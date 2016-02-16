package xword.puzzle.objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Object representing a Clue in a crossword puzzle.
 *
 * @author alex
 */
public class Clue {

    private int number; // the clue number. combine with direction to uniquely identify this clue in a puzzle.
    private Direction direction; // the clue direction. combine with number to uniquely identify this clue in a puzzle.
    private String text; // the clue text (prompt)
    private List<String> answer; // the answer values, divided into box values

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
