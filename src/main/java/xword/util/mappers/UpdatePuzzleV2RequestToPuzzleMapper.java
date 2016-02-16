package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.beans.UpdatePuzzleRequest;
import xword.puzzle.controller.beans.UpdatePuzzleV2Request;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMappingStrategy;

/**
 * Maps a UpdatePuzzleV2Request to a Puzzle object.
 *
 * @author alex
 */
@Component
public class UpdatePuzzleV2RequestToPuzzleMapper implements EntityMappingStrategy<UpdatePuzzleV2Request,Puzzle> {

    @Override
    public Puzzle map(UpdatePuzzleV2Request source) {
        if (source == null) {
            return null;
        }

        Puzzle puzzle = new Puzzle();
        puzzle.setPuzzleEditId(source.getEditId());
        puzzle.setBoardV2(source.getBoard());
        puzzle.setClues(source.getClues());
        puzzle.setMetadata(source.getMetadata());

        return puzzle;
    }
}
