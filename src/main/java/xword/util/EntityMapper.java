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
 * Utility manager that handles mapping objects.
 *
 * On application initialization, scans for components implementing {@link EntityMappingStrategy} and adds
 * them to the entityMap. Then, when {@link EntityMapper#map(Object, Class)} is called, gets the relevant
 * mapper and uses it to convert the objects.
 *
 * @author alex
 */
@Component
public class EntityMapper {

    /**
     * Object representing a map between a source Type and destination Type.
     *
     * Used as a key in the entityMap.
     */
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

    /**
     * Initialize the entityMap from all beans in the application context implementing {@link EntityMappingStrategy}.
     */
    @PostConstruct
    private void init() {
        Map<String, EntityMappingStrategy> map = context.getBeansOfType(EntityMappingStrategy.class);
        entityMap = new HashMap<>(map.size());

        // for each bean implementing EntityMappingStrategy
        for (String key : map.keySet()) {
            EntityMappingStrategy ems = map.get(key);

            // get the Type params from the implementing bean and convert it to a MapperPair
            MapperPair mapper = this._findMapperPairForMapper(ems);

            // we can only have one strategy per MapperPair...
            if (entityMap.containsKey(mapper)) {
                throw new IllegalStateException(
                        "exception while attempting to initialize EntityMapper: duplicate mapper between " +
                        mapper.getSource() +
                        " -> " +
                        mapper.getDestination());
            }

            // add the MapperPair -> EntityMappingStrategy map to the entityMap
            entityMap.put(mapper, ems);
        }
    }

    /**
     * Get the MapperPair for a specified EntityMappingStrategy.
     *
     * @param ems the EntityMappingStrategy
     * @return the source, destination pair for the EntityMappingStrategy
     */
    private MapperPair _findMapperPairForMapper(EntityMappingStrategy ems) {
        // go through all the interfaces that the EntityMappingStrategy object implements
        for (Type type : ems.getClass().getGenericInterfaces()) {
            ParameterizedType pt = (ParameterizedType) type;

            // we're looking for the EntityMappingStrategy interface
            if (pt.getRawType().equals(EntityMappingStrategy.class)) {

                // get the type arguments
                Type[] types = pt.getActualTypeArguments();
                if (types.length != 2) {
                    // there should be two. (source, destination)
                    throw new IllegalStateException(
                            "exception while attempting to initialize EntityMapper: we expected type " +
                            EntityMappingStrategy.class.getTypeName() +
                            " to contain two type arguments.");
                }

                // make the mapper pair
                return new MapperPair(types[0], types[1]);
            }
        }
        // we failed to get the MapperPair
        throw new IllegalStateException("exception while attempting to initialize EntityMapper: couldn't decipher EntityMappingStrategy: " + ems.getClass().getName());
    }


    /**
     * Map a source object to a new instance of DestinationClass.
     *
     * @param source the source
     * @return an instance of DestinationClass
     */
    @SuppressWarnings("unchecked")
    public <T> T map(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        } else if (destinationClass == null) {
            throw new EntityMappingException("exception while attempting to map entities: destination class was null");
        }
        MapperPair pair = new MapperPair(source.getClass(), destinationClass);
        if (entityMap.containsKey(pair)) {
            return (T) entityMap.get(pair).map(source);
        } else {
            throw new EntityMappingException("exception while attempting to map " + source.getClass().getName() + " to " + destinationClass.getName() + ". couldn't find a mapper.");
        }
    }
}
