package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.beans.CreatePuzzleRequest;
import xword.puzzle.controller.beans.CreatePuzzleV2Request;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMappingStrategy;

/**
 * Maps a CreatePuzzleV2Request to a Puzzle object.
 *
 * @author alex
 */
@Component
public class CreatePuzzleV2RequestToPuzzleMapper implements EntityMappingStrategy<CreatePuzzleV2Request,Puzzle> {

    @Override
    public Puzzle map(CreatePuzzleV2Request source) {
        if (source == null) {
            return null;
        }

        Puzzle puzzle = new Puzzle();
        puzzle.setBoardV2(source.getBoard());
        puzzle.setClues(source.getClues());
        puzzle.setMetadata(source.getMetadata());

        return puzzle;
    }
}
