package xword.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import xword.util.exception.EntityMappingException;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alex
 */
@Component
public class EntityMapper {

    class MapperPair {
        private Type source;
        private Type destination;

        public MapperPair(Type source, Type destination) {
            this.source = source;
            this.destination = destination;
        }

        public Type getSource() {
            return source;
        }

        public void setSource(Type source) {
            this.source = source;
        }

        public Type getDestination() {
            return destination;
        }

        public void setDestination(Type destination) {
            this.destination = destination;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }

            if (o == null || getClass() != o.getClass()) { return false; }

            MapperPair that = (MapperPair) o;

            return new EqualsBuilder()
                    .append(source, that.source)
                    .append(destination, that.destination)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(source)
                    .append(destination)
                    .toHashCode();
        }
    }

    @Autowired
    private ApplicationContext context;

    private Map<MapperPair, EntityMappingStrategy> entityMap;

    @PostConstruct
    private void init() {
        Map<String, EntityMappingStrategy> map = context.getBeansOfType(EntityMappingStrategy.class);
        entityMap = new HashMap<>(map.size());
        for (String key : map.keySet()) {
            EntityMappingStrategy ems = map.get(key);
            MapperPair mapper = this._findMapperPairForMapper(ems);
            if (mapper != null) {
                entityMap.put(mapper, ems);
            } else {
                throw new IllegalStateException("exception while attempting to initialize EntityMapper: couldn't decipher EntityMappingStrategy: " + ems.getClass().getName());
            }
        }
    }

    private MapperPair _findMapperPairForMapper(EntityMappingStrategy ems) {
        for (Type type : ems.getClass().getGenericInterfaces()) {
            ParameterizedType pt = (ParameterizedType) type;
            if (pt.getRawType().equals(EntityMappingStrategy.class)) {
                Type[] types = pt.getActualTypeArguments();
                if (types.length != 2) {
                    throw new IllegalStateException(
                            "exception while attempting to initialize EntityMapper: we expected type " +
                            EntityMappingStrategy.class.getTypeName() +
                            " to contain two type arguments.");
                }
                MapperPair mapper = new MapperPair(types[0], types[1]);
                if (entityMap.containsKey(mapper)) {
                    throw new IllegalStateException(
                            "exception while attempting to initialize EntityMapper: duplicate mapper between " +
                            types[0].getTypeName() +
                            " -> " +
                            types[1].getTypeName());
                }
                return mapper;
            }
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public <T> T map(Object source, Class<T> destinationClass) {
        MapperPair pair = new MapperPair(source.getClass(), destinationClass);
        if (entityMap.containsKey(pair)) {
            return (T) entityMap.get(pair).map(source);
        } else {
            throw new EntityMappingException("exception while attempting to map " + source.getClass().getName() + " to " + destinationClass.getName() + ". couldn't find a mapper.");
        }
    }
}
