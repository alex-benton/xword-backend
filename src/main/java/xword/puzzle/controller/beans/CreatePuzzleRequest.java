package xword.puzzle.controller.beans;

import xword.puzzle.objects.Clue;
import xword.puzzle.objects.PuzzleMetadata;

import java.util.List;

/**
 * An object representing a request to create a puzzle.
 *
 * @author alex
 */
public class CreatePuzzleRequest {

    private List<List<String>> board;
    private List<Clue> clues;
    private PuzzleMetadata metadata;

    public PuzzleMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PuzzleMetadata metadata) {
        this.metadata = metadata;
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
}
