package xword.puzzle.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xword.puzzle.manager.AnswerManager;
import xword.puzzle.manager.PuzzleManager;
import xword.puzzle.objects.Clue;
import xword.puzzle.objects.Direction;
import xword.puzzle.objects.Puzzle;
import xword.util.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alex
 */
@Component
public class AnswerManagerImpl implements AnswerManager {

    @Autowired
    private PuzzleManager puzzleManager;

    @Override
    public boolean verifyCharacterAnswer(Character c, int x, int y, String puzzleId) {
        return c.equals(this.getCharacterAnswer(x, y, puzzleId));
    }

    @Override
    public List<Boolean> verifyClueAnswer(List<Character> c, int clueNumber, Direction direction, String puzzleId) {
        Puzzle puzzle = puzzleManager.get(puzzleId);
        if (puzzle == null) {
            throw new InvalidInputException("couldn't find puzzle for puzzleId: " + puzzleId);
        }

        List<Character> answer = this.getClueAnswer(clueNumber, direction, puzzleId);

        if (answer.size() != c.size()) {
            throw new InvalidInputException("the provided answer is sized incorrectly. size was: " + c.size() + ", expected: " + answer.size());
        }

        List<Boolean> verifiedClue = new ArrayList<>(answer.size());

        for (int i = 0; i < answer.size(); i++) {
            verifiedClue.add(answer.get(i).equals(c.get(i)));
        }

        return verifiedClue;
    }

    @Override
    public List<List<Boolean>> verifyBoardAnswer(List<List<Character>> board, String puzzleId) {
        List<List<Character>> answer = this.getBoardAnswer(puzzleId);

        if (answer.size() != board.size()) {
            throw new InvalidInputException("the provided answer board is sized incorrectly. height was: " + board.size() + ", expected: " + answer.size());
        }

        List<List<Boolean>> verifiedBoard = new ArrayList<>(answer.size());
        for (int y = 0; y < board.size(); y++) {
            if (board.get(y).size() != answer.get(y).size()) {
                throw new InvalidInputException("the provided answer board is sized incorrectly. width was: " + board.get(y).size() + ", expected: " + answer.get(y).size());
            }

            List<Boolean> verifiedRow = new ArrayList<>(answer.get(y).size());
            for (int x = 0; x < board.get(y).size(); x++) {
                verifiedRow.add(board.get(y).get(x).equals(answer.get(y).get(x)));
            }

            verifiedBoard.add(verifiedRow);
        }

        return verifiedBoard;
    }

    @Override
    public Character getCharacterAnswer(int x, int y, String puzzleId) {
        if (x < 0 || y < 0) {
            throw new InvalidInputException("the provided coordinates (" + x + "," + y + ") were invalid.");
        }
        Puzzle puzzle = puzzleManager.get(puzzleId);

        if (puzzle == null) {
            throw new InvalidInputException("couldn't find puzzle for puzzleId: " + puzzleId);
        }

        if (puzzle.getBoard().size() < y || puzzle.getBoard().get(y).size() > x) {
            throw new InvalidInputException("the provided coordinates (" + x + "," + y + ") were out of range of puzzle: " + puzzleId);
        }

        return puzzle.getBoard().get(y).get(x);
    }

    @Override
    public List<Character> getClueAnswer(int clueNumber, Direction direction, String puzzleId) {
        Puzzle puzzle = puzzleManager.get(puzzleId);

        if (puzzle == null) {
            throw new InvalidInputException("couldn't find puzzle for puzzleId: " + puzzleId);
        }

        for (Clue clue : puzzle.getClues()) {
            if (clue.getDirection().equals(direction) && clue.getNumber() == clueNumber) {
                return clue.getAnswer();
            }
        }

        throw new InvalidInputException("couldn't find clue " + clueNumber + " " + direction.toString() + " in puzzle: " + puzzleId);
    }

    @Override
    public List<List<Character>> getBoardAnswer(String puzzleId) {
        Puzzle puzzle = puzzleManager.get(puzzleId);

        if (puzzle == null) {
            throw new InvalidInputException("couldn't find puzzle for puzzleId: " + puzzleId);
        }

        return puzzle.getBoard();

    }
}
