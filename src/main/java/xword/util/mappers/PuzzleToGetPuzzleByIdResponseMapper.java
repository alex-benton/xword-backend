package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.beans.GetPuzzleByIdResponse;
import xword.puzzle.objects.Clue;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps a Puzzle to a GetPuzzleByIdResponse object.
 *
 * @author alex
 */
@Component
public class PuzzleToGetPuzzleByIdResponseMapper implements EntityMappingStrategy<Puzzle, GetPuzzleByIdResponse> {

    @Override
    public GetPuzzleByIdResponse map(Puzzle source) {
        if (source == null) {
            return null;
        } else {
            GetPuzzleByIdResponse result = new GetPuzzleByIdResponse();
            result.setId(source.getPuzzleId());
            result.setClues(this.mapClues(source.getClues()));
            result.setBoard(this.mapBoard(source.getBoard()));
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
    private List<GetPuzzleByIdResponse.ResponseClue> mapClues(List<Clue> clues) {
        if (clues == null) {
            return null;
        } else {
            return (clues.stream().map(GetPuzzleByIdResponse.ResponseClue::new).collect(Collectors.toList()));
        }
    }

    /**
     * Obfuscate a board by replacing the answer values with blank spaces.
     *
     * @param board
     * @return the obfuscated board
     */
    private List<List<String>> mapBoard(List<List<String>> board) {
        if (board == null) {
            return null;
        } else {
            List<List<String>> result = new ArrayList<>(board.size());
            for (List<String> row : board) {
                result.add(row.stream().map(c -> c == null ? null : " ").collect(Collectors.toList()));
            }
            return result;
        }
    }
}
