package xword.puzzle.manager;

import xword.puzzle.objects.Puzzle;

/**
 * @author alex
 */
public interface PuzzleManager {

    /**
     * Get the Puzzle with the provided id. Returns null if a Puzzle can't be found for the provided id.
     *
     * @param id the id of the puzzle to get.
     * @return the found Puzzle, or null if a Puzzle couldn't be found.
     */
    Puzzle get(String id);

    Puzzle save(Puzzle puzzle);
}
