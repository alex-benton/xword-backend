package xword.puzzle.objects;

import java.util.Map;

/**
 * Object representing a Box in a crossword board.
 *
 * @author alex
 */
public class Box {

    private String value; // a String (vs. a char) to support rebus crossword values
    private Map<String, String> attributes; // stores special attributes of the box, defined by the front-end
                                            // to support special types of crossword puzzles

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
