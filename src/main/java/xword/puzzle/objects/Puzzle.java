package xword.puzzle.objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by alex on 12/13/15.
 */
public class Puzzle {

    @Id
    private String puzzleId;
    private String puzzleEditId;
    private List<List<String>> board;
    private List<List<Box>> boardV2;
    private List<Clue> clues;
    private PuzzleMetadata metadata;
    private Long createdDate;
    private Long modifiedDate;

    public String getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(String puzzleId) {
        this.puzzleId = puzzleId;
    }

    public String getPuzzleEditId() {
        return puzzleEditId;
    }

    public void setPuzzleEditId(String puzzleEditId) {
        this.puzzleEditId = puzzleEditId;
    }

    public List<List<String>> getBoard() {
        return board;
    }

    public void setBoard(List<List<String>> board) {
        this.board = board;
    }

    public List<Clue> getClues() {
        return clues;
    }

    public void setClues(List<Clue> clues) {
        this.clues = clues;
    }

    public PuzzleMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PuzzleMetadata metadata) {
        this.metadata = metadata;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<List<Box>> getBoardV2() {
        return boardV2;
    }

    public void setBoardV2(List<List<Box>> boardV2) {
        this.boardV2 = boardV2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("puzzleId", puzzleId)
                .append("puzzleEditId", puzzleEditId)
                .append("board", board)
                .append("clues", clues)
                .append("metadata", metadata)
                .append("createdDate", createdDate)
                .append("modifiedDate", modifiedDate)
                .toString();
    }
}
