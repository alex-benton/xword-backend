package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author alex
 */
public class GetClueAnswerResponse {

    private List<String> answer;

    public GetClueAnswerResponse(List<String> answer) {
        this.answer = answer;
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
                .append("answer", answer)
                .toString();
    }
}
