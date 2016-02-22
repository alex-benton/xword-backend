package xword.puzzle.manager.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xword.puzzle.controller.exception.PuzzleNotFoundException;
import xword.puzzle.manager.AnswerManager;
import xword.puzzle.manager.PuzzleManager;
import xword.puzzle.objects.Clue;
import xword.puzzle.objects.Direction;
import xword.puzzle.objects.Puzzle;
import xword.puzzle.util.PuzzleHelper;
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

    /**
     * Evaluate whether the provided 'guess' matches the value found at (x,y) in the Puzzle indicated by
     * the specified puzzleId.
     *
     * @param guess the 'guess' value
     * @param x the x position of the Box we're guessing
     * @param y the y position of the Box we're guessing
     * @param puzzleId the puzzleId of the puzzle we're guessing for
     * @return true if the guess is correct, false otherwise
     */
    @Override
    public boolean verifyCharacterAnswer(String guess, int x, int y, String puzzleId) {
        if (guess == null) {
            return false;
        }

        // verify the guess
        return guess.equals(this.getCharacterAnswer(x, y, puzzleId));
    }

    /**
     * Evaluate whether the provided 'guess' matches the Clue with direction and clueNumber in the Puzzle
     * indicated by the specified puzzleId.
     *
     * @param guess the 'guess' value
     * @param clueNumber the clueNumber of the Clue we're guessing
     * @param direction the direction of the Clue we're guessing
     * @param puzzleId the puzzleId of the puzzle we're guessing for
     * @return  a List<Boolean>. contains 'true' or 'false' for each value in 'guess', indicating that the
     *          box value was valid or invalid respectively.
     */
    @Override
    public List<Boolean> verifyClueAnswer(List<String> guess, int clueNumber, Direction direction, String puzzleId) {
        Puzzle puzzle = puzzleManager.get(puzzleId);
        if (puzzle == null) {
            throw new PuzzleNotFoundException("couldn't find puzzle for puzzleId: " + puzzleId);
        }

        List<String> answer = this.getClueAnswer(clueNumber, direction, puzzleId);

        if (answer.size() != guess.size()) {
            throw new InvalidInputException("the provided answer is sized incorrectly. size was: " + guess.size() + ", expected: " + answer.size());
        }

        List<Boolean> verifiedClue = new ArrayList<>(answer.size());

        // verify the values in the guess
        for (int i = 0; i < answer.size(); i++) {
            verifiedClue.add(answer.get(i).equals(guess.get(i)));
        }

        return verifiedClue;
    }

    /**
     * Evaluate whether the provided 'guess' matches the board in the Puzzle indicated by the specified
     * puzzleId.
     *
     * @param guess the 'guess' value
     * @param puzzleId the puzzleId of the puzzle we're guessing for
     * @return  a List<List<Boolean>>. contains 'true' or 'false' for each value in 'guess', indicating that
     *          the box value was valid or invalid respectively.
     */
    @Override
    public List<List<Boolean>> verifyBoardAnswer(List<List<String>> guess, String puzzleId) {
        List<List<String>> answer = this.getBoardAnswer(puzzleId);

        if (answer.size() != guess.size()) {
            throw new InvalidInputException("the provided answer guess is sized incorrectly. height was: " + guess.size() + ", expected: " + answer.size());
        }

        List<List<Boolean>> verifiedBoard = new ArrayList<>(answer.size());

        // verify the guess
        for (int y = 0; y < guess.size(); y++) {
            if (guess.get(y).size() != answer.get(y).size()) {
                throw new InvalidInputException("the provided answer guess is sized incorrectly. width was: " + guess.get(y).size() + ", expected: " + answer.get(y).size());
            }

            List<Boolean> verifiedRow = new ArrayList<>(answer.get(y).size());
            for (int x = 0; x < guess.get(y).size(); x++) {
                String actual = guess.get(y).get(x);
                String expected = answer.get(y).get(x);
                verifiedRow.add((actual == null && expected == null) || StringUtils.equalsIgnoreCase(actual, expected));
            }

            verifiedBoard.add(verifiedRow);
        }

        return verifiedBoard;
    }

    /**
     * Get the value for the box at (x,y) in the Puzzle indicated by the specified puzzleId.
     *
     * @param x the x position of the box we want answered
     * @param y the y position of the box we want answered
     * @param puzzleId the puzzleId of the puzzle we want answered
     * @return  the value of the box at position (x,y)
     */
    @Override
    public String getCharacterAnswer(int x, int y, String puzzleId) {
        if (x < 0 || y < 0) {
            throw new InvalidInputException("the provided coordinates (" + x + "," + y + ") were invalid.");
        }
        Puzzle puzzle = puzzleManager.get(puzzleId);

        if (puzzle == null) {
            throw new PuzzleNotFoundException("couldn't find puzzle for puzzleId: " + puzzleId);
        }

        // try to get the v2 board first before failing back to v1
        if (puzzle.getBoardV2() != null) {
            if (y >= puzzle.getBoardV2().size() || x >= puzzle.getBoardV2().get(y).size()) {
                throw new InvalidInputException("the provided coordinates (" +
                                                x +
                                                "," +
                                                y +
                                                ") were out of range of puzzle: " +
                                                puzzleId);
            }
            return puzzle.getBoardV2().get(y).get(x).getValue();
        } else if (puzzle.getBoard() != null) {
            if (y >= puzzle.getBoard().size() || x >= puzzle.getBoard().get(y).size()) {
                throw new InvalidInputException("the provided coordinates (" +
                                                x +
                                                "," +
                                                y +
                                                ") were out of range of puzzle: " +
                                                puzzleId);
            }
            return puzzle.getBoard().get(y).get(x);
        } else {
            throw new PuzzleNotFoundException("couldn't find puzzle for puzzleId: " + puzzleId);
        }
    }

    /**
     * Get the value for the Clue with provided direction/clueNumber in the Puzzle indicated by the
     * specified puzzleId.
     *
     * @param clueNumber the clueNumber of the Clue we want answered
     * @param direction the direction of the Clue we want answered
     * @param puzzleId the puzzleId of the puzzle we want answered
     * @return  the value of the Clue
     */
    @Override
    public List<String> getClueAnswer(int clueNumber, Direction direction, String puzzleId) {
        Puzzle puzzle = puzzleManager.get(puzzleId);

        if (puzzle == null) {
            throw new PuzzleNotFoundException("couldn't find puzzle for puzzleId: " + puzzleId);
        }

        for (Clue clue : puzzle.getClues()) {
            if (clue.getDirection().equals(direction) && clue.getNumber() == clueNumber) {
                return clue.getAnswer();
            }
        }

        throw new InvalidInputException("couldn't find clue " + clueNumber + " " + direction.toString() + " in puzzle: " + puzzleId);
    }

    /**
     * Get the value for the board in the Puzzle indicated by the specified puzzleId.
     *
     * @param puzzleId the puzzleId of the puzzle we want answered
     * @return  the value of the board
     */
    @Override
    public List<List<String>> getBoardAnswer(String puzzleId) {
        Puzzle puzzle = puzzleManager.get(puzzleId);

        if (puzzle == null) {
            throw new PuzzleNotFoundException("couldn't find puzzle for puzzleId: " + puzzleId);
        }

        if (puzzle.getBoardV2() != null) {
            return PuzzleHelper.convertV2Board(puzzle.getBoardV2());
        } else if (puzzle.getBoard() != null) {
            return puzzle.getBoard();
        }

        throw new InvalidInputException("couldn't find puzzle for puzzleId: " + puzzleId);
    }
}