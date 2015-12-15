package xword.puzzle.controller.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xword.puzzle.objects.Clue;

import java.util.List;

/**
 * @author alex
 */
public class GetPuzzleByEditIdResponse {

    private String id;
    private String editId;
    private List<List<Character>> board;
    private List<Clue> clues;

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

    public List<List<Character>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Character>> board) {
        this.board = board;
    }

    public List<Clue> getClues() {
        return clues;
    }

    public void setClues(List<Clue> clues) {
        this.clues = clues;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("editId", editId)
                .append("board", board)
                .append("clues", clues)
                .toString();
    }
}
