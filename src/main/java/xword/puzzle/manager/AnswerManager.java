package xword.puzzle.manager;

import xword.puzzle.objects.Direction;

import java.util.List;

/**
 * @author alex
 */
public interface AnswerManager {

    boolean verifyCharacterAnswer(Character c, int x, int y, String puzzleId);

    List<Boolean> verifyClueAnswer(List<Character> c, int clueNumber, Direction direction, String puzzleId);

    List<List<Boolean>> verifyBoardAnswer(List<List<Character>> board, String puzzleId);

    Character getCharacterAnswer(int x, int y, String puzzleId);

    List<Character> getClueAnswer(int clueNumber, Direction direction, String puzzleId);

    List<List<Character>> getBoardAnswer(String puzzleId);

}
