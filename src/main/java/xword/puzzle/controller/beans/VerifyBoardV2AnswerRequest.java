package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Box;

import java.util.List;

/**
 * @author alex
 */
public class VerifyBoardV2AnswerRequest {

    private List<List<Box>> answer;

    public List<List<Box>> getAnswer() {
        return answer;
    }

    public void setAnswer(List<List<Box>> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("answer", answer)
                .toString();
    }
}
