package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Direction;

import java.util.List;

/**
 * An object representing a request to verify the answer for the clue with specified number and direction.
 *
 * @author alex
 */
public class VerifyClueAnswerRequest {

    private int number;
    private Direction direction;
    private List<String> answer;

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

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", number)
                .append("direction", direction)
                .append("answer", answer)
                .toString();
    }
}
