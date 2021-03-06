package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.beans.GetPuzzleByEditIdResponse;
import xword.puzzle.controller.beans.GetPuzzleV2ByEditIdResponse;
import xword.puzzle.objects.Box;
import xword.puzzle.objects.Puzzle;
import xword.puzzle.util.PuzzleHelper;
import xword.util.EntityMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps a Puzzle to a GetPuzzleV2ByEditIdResponse object.
 *
 * @author alex
 */
@Component
public class PuzzleToGetPuzzleV2ByEditIdResponseMapper implements EntityMappingStrategy<Puzzle, GetPuzzleV2ByEditIdResponse> {

    @Override
    public GetPuzzleV2ByEditIdResponse map(Puzzle source) {
        if (source == null) {
            return null;
        }

        GetPuzzleV2ByEditIdResponse result = new GetPuzzleV2ByEditIdResponse();
        result.setId(source.getPuzzleId());
        result.setEditId(source.getPuzzleEditId());
        result.setClues(source.getClues());

        if (source.getBoardV2() != null) {
            result.setBoard(source.getBoardV2());
        } else {
            // if we have a V1 board, convert it to a V2 board for the response.
            result.setBoard(PuzzleHelper.convertV1Board(source.getBoard()));
        }

        result.setMetadata(source.getMetadata());
        result.setCreatedDate(source.getCreatedDate());
        result.setModifiedDate(source.getModifiedDate());

        return result;
    }
}
