package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * An object representing the response to a 'verify char answer' request. 'answer' will contain true if the request
 * was correct, false otherwise.
 *
 * @author alex
 */
public class VerifyCharAnswerResponse {

    private boolean answer;

    public VerifyCharAnswerResponse(boolean answer) {
        this.answer = answer;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("answer", answer)
                .toString();
    }
}
