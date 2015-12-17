package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author alex
 */
public class GetCharAnswerResponse {

    private char answer;

    public GetCharAnswerResponse(char answer) {
        this.answer = answer;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("answer", answer)
                .toString();
    }
}
