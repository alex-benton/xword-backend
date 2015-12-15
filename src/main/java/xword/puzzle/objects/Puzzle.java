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
    private List<List<Character>> board;
    private List<Clue> clues;

    public List<Clue> getClues() {
        return clues;
    }

    public void setClues(List<Clue> clues) {
        this.clues = clues;
    }

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

    public List<List<Character>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Character>> board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("puzzleId", puzzleId)
                .append("puzzleEditId", puzzleEditId)
                .append("board", board)
                .append("clues", clues)
                .toString();
    }
}
