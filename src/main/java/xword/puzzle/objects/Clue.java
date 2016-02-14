package xword.puzzle.objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author alex
 */
public class Clue {

    private int number;
    private Direction direction;
    private String text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
                .append("text", text)
                .append("answer", answer)
                .toString();
    }
}
