package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * An object representing a request to verify the box value at the provided coordinates.
 *
 * @author alex
 */
public class VerifyCharAnswerRequest {

    private int x;
    private int y;
    private String character;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("x", x)
                .append("y", y)
                .append("character", character)
                .toString();
    }
}
