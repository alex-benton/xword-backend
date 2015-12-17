package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.beans.UpdatePuzzleRequest;
import xword.puzzle.controller.beans.UpdatePuzzleResponse;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMappingStrategy;

/**
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
        response.setBoard(source.getBoard());
        response.setClues(source.getClues());
        return response;
    }
}
