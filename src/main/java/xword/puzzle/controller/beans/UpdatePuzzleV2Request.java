package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Box;
import xword.puzzle.objects.Clue;
import xword.puzzle.objects.PuzzleMetadata;

import java.util.List;

/**
 * An object representing a request to update a puzzle.
 *
 * A V2 puzzle uses Box objects for the board, instead of strings.
 *
 * @author alex
 */
public class UpdatePuzzleV2Request {

    private String editId;
    private List<Clue> clues;
    private List<List<Box>> board;
    private PuzzleMetadata metadata;

    public String getEditId() {
        return editId;
    }

    public void setEditId(String editId) {
        this.editId = editId;
    }

    public List<Clue> getClues() {
        return clues;
    }

    public void setClues(List<Clue> clues) {
        this.clues = clues;
    }

    public List<List<Box>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Box>> board) {
        this.board = board;
    }

    public PuzzleMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PuzzleMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("editId", editId)
                .append("clues", clues)
                .append("board", board)
                .append("metadata", metadata)
                .toString();
    }
}
