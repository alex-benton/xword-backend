package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
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
