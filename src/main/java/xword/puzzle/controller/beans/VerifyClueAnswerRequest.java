package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Direction;

import java.util.List;

/**
 * @author alex
 */
public class VerifyClueAnswerRequest {

    private int number;
    private Direction direction;
    private List<Character> answer;

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

    public List<Character> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Character> answer) {
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
