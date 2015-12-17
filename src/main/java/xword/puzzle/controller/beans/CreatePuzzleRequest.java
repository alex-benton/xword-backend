package xword.puzzle.controller.beans;

import xword.puzzle.objects.Clue;

import java.util.List;

/**
 * @author alex
 */
public class CreatePuzzleRequest {

    private List<List<Character>> board;
    private List<Clue> clues;

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
}
