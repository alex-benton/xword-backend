package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * An object representing the response to a 'get char answer' request. 'answer' will contain the value
 * of a single Box.
 *
 * @author alex
 */
public class GetCharAnswerResponse {

    private String answer;

    public GetCharAnswerResponse(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("answer", answer)
                .toString();
    }
}
