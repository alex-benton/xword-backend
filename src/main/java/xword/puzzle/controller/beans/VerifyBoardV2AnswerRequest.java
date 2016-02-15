package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Box;

import java.util.List;

/**
 * An object representing a request to verify a provided board.
 *
 * A V2 puzzle uses Box objects for the board, instead of strings. However, only the value of the Box is checked for
 * the verify request.
 *
 * @author alex
 */
public class VerifyBoardV2AnswerRequest {

    private List<List<Box>> answer;

    public List<List<Box>> getAnswer() {
        return answer;
    }

    public void setAnswer(List<List<Box>> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("answer", answer)
                .toString();
    }
}
