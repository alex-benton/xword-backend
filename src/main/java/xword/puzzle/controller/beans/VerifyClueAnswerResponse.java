package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
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
