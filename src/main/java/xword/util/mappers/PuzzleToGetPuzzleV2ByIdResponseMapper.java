package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.beans.GetPuzzleByIdResponse;
import xword.puzzle.controller.beans.GetPuzzleV2ByIdResponse;
import xword.puzzle.objects.Box;
import xword.puzzle.objects.Clue;
import xword.puzzle.objects.Puzzle;
import xword.puzzle.util.PuzzleHelper;
import xword.util.EntityMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps a Puzzle to a GetPuzzleV2ByIdResponse object.
 *
 * @author alex
 */
@Component
public class PuzzleToGetPuzzleV2ByIdResponseMapper implements EntityMappingStrategy<Puzzle, GetPuzzleV2ByIdResponse> {

    @Override
    public GetPuzzleV2ByIdResponse map(Puzzle source) {
        if (source == null) {
            return null;
        } else {
            GetPuzzleV2ByIdResponse result = new GetPuzzleV2ByIdResponse();
            result.setId(source.getPuzzleId());
            result.setClues(this.mapClues(source.getClues()));

            if (source.getBoardV2() != null) {
                // if we have a v2 board, obfuscate it
                result.setBoard(PuzzleHelper.obfuscateV2Board(source.getBoardV2()));
            } else {
                // if we have a v1 board, obfuscate and convert it to v2
                result.setBoard(PuzzleHelper.obfuscateV1Board(source.getBoard()));
            }

            result.setMetadata(source.getMetadata());
            result.setCreatedDate(source.getCreatedDate());
            result.setModifiedDate(source.getModifiedDate());

            return result;
        }
    }

    /**
     * Obfuscate the clues by mapping the Clue objects to ResponseClues (without answers)
     *
     * @param clues the clue list
     * @return the obfuscated clues
     */
    private List<GetPuzzleV2ByIdResponse.ResponseClue> mapClues(List<Clue> clues) {
        if (clues == null) {
            return null;
        } else {
            return (clues.stream().map(GetPuzzleV2ByIdResponse.ResponseClue::new).collect(Collectors.toList()));
        }
    }
}
