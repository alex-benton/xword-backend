package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author alex
 */
public class GetBoardAnswerResponse {

    private List<List<Character>> answer;

    public GetBoardAnswerResponse(List<List<Character>> answer) {
        this.answer = answer;
    }

    public List<List<Character>> getAnswer() {
        return answer;
    }

    public void setAnswer(List<List<Character>> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("answer", answer)
                .toString();
    }
}
