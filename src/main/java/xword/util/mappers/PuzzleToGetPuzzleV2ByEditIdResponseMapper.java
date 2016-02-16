package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.beans.GetPuzzleByEditIdResponse;
import xword.puzzle.controller.beans.GetPuzzleV2ByEditIdResponse;
import xword.puzzle.objects.Box;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
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
            result.setBoard(this.mapLegacyBoard(source.getBoard()));
        }

        result.setMetadata(source.getMetadata());
        result.setCreatedDate(source.getCreatedDate());
        result.setModifiedDate(source.getModifiedDate());

        return result;
    }

    private List<List<Box>> mapLegacyBoard(List<List<String>> board) {
        if (board == null) {
            return null;
        } else {
            return board.stream().map(
                    row -> row.stream().map(
                            (string) -> {
                                Box b = new Box();
                                b.setAttributes(null);
                                b.setValue(string);
                                return b;
                            }
                    ).collect(Collectors.toList())
                ).collect(Collectors.toList());
        }
    }
}
