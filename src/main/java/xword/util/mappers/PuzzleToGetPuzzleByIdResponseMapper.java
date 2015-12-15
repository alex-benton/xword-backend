package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.entities.GetPuzzleByIdResponse;
import xword.puzzle.objects.Clue;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
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
            return result;
        }
    }

    private List<GetPuzzleByIdResponse.ResponseClue> mapClues(List<Clue> clues) {
        if (clues == null) {
            return null;
        } else {
            return (clues.stream().map(GetPuzzleByIdResponse.ResponseClue::new).collect(Collectors.toList()));
        }
    }

    private List<List<Character>> mapBoard(List<List<Character>> board) {
        if (board == null) {
            return null;
        } else {
            List<List<Character>> result = new ArrayList<>(board.size());
            for (List<Character> row : board) {
                result.add(row.stream().map(c -> c == null ? null : ' ').collect(Collectors.toList()));
            }
            return result;
        }
    }
}
