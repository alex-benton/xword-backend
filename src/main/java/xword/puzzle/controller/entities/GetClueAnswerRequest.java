package xword.puzzle.controller.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Direction;

/**
 * @author alex
 */
public class GetClueAnswerRequest {

    private int number;
    private Direction direction;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", number)
                .append("direction", direction)
                .toString();
    }
}
