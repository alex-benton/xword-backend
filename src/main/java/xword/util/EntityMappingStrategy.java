package xword.util;

/**
 * @author alex
 */
public interface EntityMappingStrategy<SourceClass, DestinationClass> {

    /**
     * Map an instance of SourceClass to a new instance of DestinationClass.
     *
     * @param source the source
     * @return the destination
     */
    DestinationClass map(SourceClass source);

}
