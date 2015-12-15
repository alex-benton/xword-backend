package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.entities.GetPuzzleByEditIdResponse;
import xword.puzzle.controller.entities.GetPuzzleByIdResponse;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMappingStrategy;

/**
 * @author alex
 */
@Component
public class PuzzleToGetPuzzleByEditIdResponseMapper implements EntityMappingStrategy<Puzzle, GetPuzzleByEditIdResponse> {

    @Override
    public GetPuzzleByEditIdResponse map(Puzzle source) {
        if (source == null) {
            return null;
        }

        GetPuzzleByEditIdResponse result = new GetPuzzleByEditIdResponse();
        result.setId(source.getPuzzleId());
        result.setEditId(source.getPuzzleEditId());
        result.setClues(source.getClues());
        result.setBoard(source.getBoard());
        return result;
    }
}
