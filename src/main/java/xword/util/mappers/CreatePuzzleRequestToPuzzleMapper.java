package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.entities.CreatePuzzleRequest;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMappingStrategy;

/**
 * @author alex
 */
@Component
public class CreatePuzzleRequestToPuzzleMapper implements EntityMappingStrategy<CreatePuzzleRequest,Puzzle> {

    @Override
    public Puzzle map(CreatePuzzleRequest source) {
        if (source == null) {
            return null;
        }

        Puzzle puzzle = new Puzzle();
        puzzle.setBoard(source.getBoard());
        puzzle.setClues(source.getClues());
        return puzzle;
    }
}
