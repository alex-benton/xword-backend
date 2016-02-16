package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * An object representing the response for a 'get board answer' request. 'answer' will contain the
 * string value of the solved board.
 *
 * @author alex
 */
public class GetBoardAnswerResponse {

    private List<List<String>> answer;

    public GetBoardAnswerResponse(List<List<String>> answer) {
        this.answer = answer;
    }

    public List<List<String>> getAnswer() {
        return answer;
    }

    public void setAnswer(List<List<String>> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("answer", answer)
                .toString();
    }
}
