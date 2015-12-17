package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author alex
 */
public class VerifyBoardAnswerResponse {

    private List<List<Boolean>> answer;

    public VerifyBoardAnswerResponse(List<List<Boolean>> answer) {
        this.answer = answer;
    }

    public List<List<Boolean>> getAnswer() {
        return answer;
    }

    public void setAnswer(List<List<Boolean>> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("answer", answer)
                .toString();
    }
}
