package xword.puzzle.controller.beans;

import xword.puzzle.objects.Box;
import xword.puzzle.objects.Clue;
import xword.puzzle.objects.PuzzleMetadata;

import java.util.List;

/**
 * An object representing a request to create a puzzle.
 *
 * A V2 puzzle uses Box objects for the board, instead of strings.
 *
 * @author alex
 */
public class CreatePuzzleV2Request {

    private List<List<Box>> board;
    private List<Clue> clues;
    private PuzzleMetadata metadata;

    public PuzzleMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PuzzleMetadata metadata) {
        this.metadata = metadata;
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

}
