package xword.util.mappers;

import org.springframework.stereotype.Component;
import xword.puzzle.controller.beans.GetPuzzleByIdResponse;
import xword.puzzle.controller.beans.GetPuzzleV2ByIdResponse;
import xword.puzzle.objects.Box;
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
                result.setBoard(this.mapBoard(source.getBoardV2()));
            } else {
                result.setBoard(this.mapLegacyBoard(source.getBoard()));
            }

            result.setMetadata(source.getMetadata());
            result.setCreatedDate(source.getCreatedDate());
            result.setModifiedDate(source.getModifiedDate());

            return result;
        }
    }

    private List<GetPuzzleV2ByIdResponse.ResponseClue> mapClues(List<Clue> clues) {
        if (clues == null) {
            return null;
        } else {
            return (clues.stream().map(GetPuzzleV2ByIdResponse.ResponseClue::new).collect(Collectors.toList()));
        }
    }

    private List<List<Box>> mapBoard(List<List<Box>> board) {
        if (board == null) {
            return null;
        } else {
            return board.stream().map(
                    row -> row.stream().map(
                            (box) -> {
                                Box b = new Box();
                                b.setAttributes(box.getAttributes());
                                b.setValue(box.getValue() == null ? null : " ");
                                return b;
                            }
                    ).collect(Collectors.toList())
                ).collect(Collectors.toList());
        }
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
                                b.setValue(string == null ? null : " ");
                                return b;
                            }
                    ).collect(Collectors.toList())
                ).collect(Collectors.toList());
        }
    }
}