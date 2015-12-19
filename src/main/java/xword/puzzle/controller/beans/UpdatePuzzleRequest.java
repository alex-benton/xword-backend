package xword.puzzle.controller.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Clue;

import java.util.List;

/**
 * @author alex
 */
public class UpdatePuzzleRequest {

    private String editId;
    private List<Clue> clues;
    private List<List<Character>> board;

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

    public List<List<Character>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Character>> board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("editId", editId)
                .append("clues", clues)
                .append("board", board)
                .toString();
    }
}
