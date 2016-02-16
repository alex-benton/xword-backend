package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Box;
import xword.puzzle.objects.Clue;
import xword.puzzle.objects.PuzzleMetadata;

import java.util.List;

/**
 * An object representing the response to a 'get puzzle' request. Returns all the information we have
 * about a Puzzle.
 *
 * A V2 puzzle uses Box objects for the board, instead of strings.
 *
 * @author alex
 */
public class GetPuzzleV2ByEditIdResponse {

    private String id;
    private String editId;
    private PuzzleMetadata metadata;
    private List<List<Box>> board;
    private List<Clue> clues;
    private Long modifiedDate;
    private Long createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEditId() {
        return editId;
    }

    public void setEditId(String editId) {
        this.editId = editId;
    }

    public List<List<Box>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Box>> board) {
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
                .append("editId", editId)
                .append("metadata", metadata)
                .append("board", board)
                .append("clues", clues)
                .toString();
    }
}
