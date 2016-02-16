package xword.puzzle.manager;

import xword.puzzle.objects.Direction;
import java.util.List;

/**
 * Interface containing helpful functions around answer requests.
 *
 * Supports getting direct answers about boxes, clues, and game boards. Also supports 'verifying' boxes,
 * clues, game boards. Verify methods take a 'guess' and return boolean values based on whether the guess
 * is correct.
 *
 * @author alex
 */
public interface AnswerManager {

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
    boolean verifyCharacterAnswer(String guess, int x, int y, String puzzleId);

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
    List<Boolean> verifyClueAnswer(List<String> guess, int clueNumber, Direction direction, String puzzleId);

    /**
     * Evaluate whether the provided 'guess' matches the board in the Puzzle indicated by the specified
     * puzzleId.
     *
     * @param guess the 'guess' value
     * @param puzzleId the puzzleId of the puzzle we're guessing for
     * @return  a List<List<Boolean>>. contains 'true' or 'false' for each value in 'guess', indicating that
     *          the box value was valid or invalid respectively.
     */
    List<List<Boolean>> verifyBoardAnswer(List<List<String>> guess, String puzzleId);

    /**
     * Get the value for the box at (x,y) in the Puzzle indicated by the specified puzzleId.
     *
     * @param x the x position of the box we want answered
     * @param y the y position of the box we want answered
     * @param puzzleId the puzzleId of the puzzle we want answered
     * @return  the value of the box at position (x,y)
     */
    String getCharacterAnswer(int x, int y, String puzzleId);

    /**
     * Get the value for the Clue with provided direction/clueNumber in the Puzzle indicated by the
     * specified puzzleId.
     *
     * @param clueNumber the clueNumber of the Clue we want answered
     * @param direction the direction of the Clue we want answered
     * @param puzzleId the puzzleId of the puzzle we want answered
     * @return  the value of the Clue
     */
    List<String> getClueAnswer(int clueNumber, Direction direction, String puzzleId);

    /**
     * Get the value for the board in the Puzzle indicated by the specified puzzleId.
     *
     * @param puzzleId the puzzleId of the puzzle we want answered
     * @return  the value of the board
     */
    List<List<String>> getBoardAnswer(String puzzleId);

}
