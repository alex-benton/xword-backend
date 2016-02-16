package xword.puzzle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xword.puzzle.objects.Puzzle;

/**
 * Datastore for Puzzles. Supports basic CRUD operations.
 *
 * Auto-implemented due to magic.
 *
 * @author alex
 */
public interface PuzzleRepository extends MongoRepository<Puzzle, String> {

    /**
     * Find the Puzzle in the database with the provided puzzleEditId.
     *
     * puzzleEditId is a unique key so this will only ever find one Puzzle.
     *
     * This is auto-implemented due to magic.
     *
     * @param puzzleEditId the puzzleEditId to find
     * @return the found Puzzle, or null if it can't be found
     */
    Puzzle findByPuzzleEditId(String puzzleEditId);
}
