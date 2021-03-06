package xword.puzzle.manager;

import xword.puzzle.objects.Puzzle;

/**
 * Interface containing helpful functions around Puzzle resources.
 *
 * Contains get, save, update functionality.
 *
 * @author alex
 */
public interface PuzzleManager {

    /**
     * Get the Puzzle with the provided id.
     * Returns null if a Puzzle can't be found for the provided id.
     *
     * @param id the id of the puzzle to get.
     * @return the found Puzzle, or null if a Puzzle couldn't be found.
     */
    Puzzle get(String id);

    /**
     * Get the Puzzle with the provided editId.
     * Returns null if a Puzzle can't be found for the provided editId.
     *
     * @param editId the editId of the puzzle to get.
     * @return the found Puzzle, or null if a Puzzle couldn't be found.
     */
    Puzzle getByEditId(String editId);

    /**
     * Save the provided Puzzle.
     *
     * @param puzzle the puzzle to save
     * @return the puzzle, possibly modified as a result of the save operation
     */
    Puzzle save(Puzzle puzzle);

    /**
     * Update the provided Puzzle.
     *
     * @param puzzle the puzzle to update
     * @return the updated puzzle
     */
    Puzzle update(Puzzle puzzle);

    /**
     * Patch an existing Puzzle with fields from the provided Puzzle.
     *
     * @param puzzle the puzzle to patch
     * @return the patched puzzle
     */
    Puzzle patch(Puzzle puzzle);

}
