package xword.puzzle.controller.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Direction;

/**
 * @author alex
 */
public class GetCharAnswerRequest {

    private int x;
    private int y;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("x", x)
                .append("y", y)
                .toString();
    }
}
