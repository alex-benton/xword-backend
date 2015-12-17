package xword.puzzle.controller.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author alex
 */
public class GetClueAnswerResponse {

    private List<Character> answer;

    public GetClueAnswerResponse(List<Character> answer) {
        this.answer = answer;
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
                .append("answer", answer)
                .toString();
    }
}
