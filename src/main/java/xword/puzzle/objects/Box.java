package xword.puzzle.objects;

import java.util.Map;

/**
 * @author alex
 */
public class Box {

    private String value;
    private Map<String, String> attributes;


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
