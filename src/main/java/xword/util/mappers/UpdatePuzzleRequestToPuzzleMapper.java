package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.beans.CreatePuzzleRequest;
import xword.puzzle.controller.beans.UpdatePuzzleRequest;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMappingStrategy;

/**
 * Maps a UpdatePuzzleRequest to a Puzzle object.
 *
 * @author alex
 */
@Component
public class UpdatePuzzleRequestToPuzzleMapper implements EntityMappingStrategy<UpdatePuzzleRequest,Puzzle> {

    @Override
    public Puzzle map(UpdatePuzzleRequest source) {
        if (source == null) {
            return null;
        }

        Puzzle puzzle = new Puzzle();
        puzzle.setPuzzleEditId(source.getEditId());
        puzzle.setBoard(source.getBoard());
        puzzle.setClues(source.getClues());
        puzzle.setMetadata(source.getMetadata());

        return puzzle;
    }
}
