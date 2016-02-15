package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * An object representing the response to a 'verify clue answer' request. 'answer' will contain a list of
 * boolean values. A 'true' boolean value represents that the square in the verify request was correct.
 * A 'false' boolean value represents that the square was wrong.
 *
 * @author alex
 */
public class VerifyClueAnswerResponse {

    private List<Boolean> answer;

    public VerifyClueAnswerResponse(List<Boolean> answer) {
        this.answer = answer;
    }

    public List<Boolean> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Boolean> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("answer", answer)
                .toString();
    }
}
