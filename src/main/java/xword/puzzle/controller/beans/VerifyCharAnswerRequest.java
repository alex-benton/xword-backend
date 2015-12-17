package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author alex
 */
public class VerifyCharAnswerRequest {

    private int x;
    private int y;
    private Character character;

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

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
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
