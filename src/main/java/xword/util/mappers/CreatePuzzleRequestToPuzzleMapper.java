package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.beans.CreatePuzzleRequest;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMappingStrategy;

/**
 * Maps a CreatePuzzleRequest to a Puzzle object.
 *
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
        puzzle.setMetadata(source.getMetadata());

        return puzzle;
    }
}
