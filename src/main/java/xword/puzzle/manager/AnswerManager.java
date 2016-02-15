package xword.puzzle.manager;

import xword.puzzle.objects.Box;
import xword.puzzle.objects.Direction;

import java.util.List;

/**
 * @author alex
 */
public interface AnswerManager {

    boolean verifyCharacterAnswer(String c, int x, int y, String puzzleId);

    List<Boolean> verifyClueAnswer(List<String> c, int clueNumber, Direction direction, String puzzleId);

    List<List<Boolean>> verifyBoardAnswer(List<List<String>> board, String puzzleId);

    String getCharacterAnswer(int x, int y, String puzzleId);

    List<String> getClueAnswer(int clueNumber, Direction direction, String puzzleId);

    List<List<String>> getBoardAnswer(String puzzleId);

}
