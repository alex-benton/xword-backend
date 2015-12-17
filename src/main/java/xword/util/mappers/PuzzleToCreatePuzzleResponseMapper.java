package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.beans.CreatePuzzleResponse;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMappingStrategy;

/**
 * @author alex
 */
@Component
public class PuzzleToCreatePuzzleResponseMapper implements EntityMappingStrategy<Puzzle, CreatePuzzleResponse> {

    @Override
    public CreatePuzzleResponse map(Puzzle source) {
        if (source == null) {
            return null;
        }

        CreatePuzzleResponse result = new CreatePuzzleResponse();
        result.setEditId(source.getPuzzleEditId());
        result.setId(source.getPuzzleId());
        return result;
    }
}
