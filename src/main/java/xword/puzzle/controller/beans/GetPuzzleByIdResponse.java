package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Box;
import xword.puzzle.objects.Clue;
import xword.puzzle.objects.Direction;
import xword.puzzle.objects.PuzzleMetadata;

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
    private List<List<String>> board;
    private List<ResponseClue> clues;
    private PuzzleMetadata metadata;
    private Long modifiedDate;
    private Long createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<List<String>> getBoard() {
        return board;
    }

    public void setBoard(List<List<String>> board) {
        this.board = board;
    }

    public List<ResponseClue> getClues() {
        return clues;
    }

    public void setClues(List<ResponseClue> clues) {
        this.clues = clues;
    }

    public PuzzleMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PuzzleMetadata metadata) {
        this.metadata = metadata;
    }

    public Long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("board", board)
                .append("clues", clues)
                .append("metadata", metadata)
                .toString();
    }
}
