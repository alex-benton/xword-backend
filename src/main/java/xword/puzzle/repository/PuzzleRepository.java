package xword.puzzle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xword.puzzle.objects.Puzzle;

/**
 * @author alex
 */
public interface PuzzleRepository extends MongoRepository<Puzzle, String> {

}
