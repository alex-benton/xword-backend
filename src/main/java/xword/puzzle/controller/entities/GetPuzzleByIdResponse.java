package xword.puzzle.controller.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Clue;
import xword.puzzle.objects.Direction;

import java.util.List;

/**
 * @author alex
 */
public class GetPuzzleByIdResponse {

    public static class ResponseClue {
        private int number;
        private Direction direction;
        private String text;

        public ResponseClue(Clue clue) {
            this.number = clue.getNumber();
            this.direction = clue.getDirection();
            this.text = clue.getText();
        }

        public String getText() {
            return text;
        }

        public Direction getDirection() {
            return direction;
        }

        public int getNumber() {
            return number;
        }
    }

    private String id;
    private List<List<Character>> board;
    private List<ResponseClue> clues;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<List<Character>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Character>> board) {
        this.board = board;
    }

    public List<ResponseClue> getClues() {
        return clues;
    }

    public void setClues(List<ResponseClue> clues) {
        this.clues = clues;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("board", board)
                .append("clues", clues)
                .toString();
    }
}
