package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Clue;
import xword.puzzle.objects.PuzzleMetadata;

import java.util.List;

/**
 * An object representing a request to update a puzzle.
 *
 * @author alex
 */
public class UpdatePuzzleRequest {

    private String editId;
    private List<Clue> clues;
    private List<List<String>> board;
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

    public List<List<String>> getBoard() {
        return board;
    }

    public void setBoard(List<List<String>> board) {
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
