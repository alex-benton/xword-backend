package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.beans.UpdatePuzzleRequest;
import xword.puzzle.controller.beans.UpdatePuzzleResponse;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMappingStrategy;

/**
 * Maps a Puzzle to a UpdatePuzzleResponse object.
 *
 * @author alex
 */
@Component
public class PuzzleToUpdatePuzzleResponseMapper implements EntityMappingStrategy<Puzzle,UpdatePuzzleResponse> {

    @Override
    public UpdatePuzzleResponse map(Puzzle source) {
        if (source == null) {
            return null;
        }

        UpdatePuzzleResponse response = new UpdatePuzzleResponse();
        response.setId(source.getPuzzleId());
        response.setEditId(source.getPuzzleEditId());
        return response;
    }
}
