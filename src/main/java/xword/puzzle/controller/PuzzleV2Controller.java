package xword.puzzle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xword.puzzle.controller.beans.*;
import xword.puzzle.controller.exception.PuzzleNotFoundException;
import xword.puzzle.manager.AnswerManager;
import xword.puzzle.manager.PuzzleManager;
import xword.puzzle.objects.Box;
import xword.puzzle.objects.Puzzle;
import xword.util.EntityMapper;

import java.util.stream.Collectors;

/**
 * @author alex
 */
@RestController
@CrossOrigin
@RequestMapping("/puzzle/v2")
public class PuzzleV2Controller {

    @Autowired
    private PuzzleManager puzzleManager;

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private AnswerManager answerManager;


    @RequestMapping(method= RequestMethod.GET, path="/{id}")
    public GetPuzzleV2ByIdResponse getPuzzleById(@PathVariable String id) throws PuzzleNotFoundException {
        Puzzle puzzle = puzzleManager.get(id);
        if (puzzle == null) {
            throw new PuzzleNotFoundException();
        }
        return entityMapper.map(puzzle, GetPuzzleV2ByIdResponse.class);
    }

    @RequestMapping(method=RequestMethod.GET, path="/edit/{editId}")
    public GetPuzzleV2ByEditIdResponse getPuzzleByEditId(@PathVariable String editId) throws PuzzleNotFoundException {
        Puzzle puzzle = puzzleManager.getByEditId(editId);
        if (puzzle == null) {
            throw new PuzzleNotFoundException();
        }
        return entityMapper.map(puzzle, GetPuzzleV2ByEditIdResponse.class);
    }


    @RequestMapping(method=RequestMethod.POST, path="", consumes="application/json")
    public CreatePuzzleResponse createPuzzle(@RequestBody CreatePuzzleV2Request request) {
        return entityMapper.map(
                puzzleManager.save(
                        entityMapper.map(request, Puzzle.class))
                , CreatePuzzleResponse.class);
    }

    @RequestMapping(method=RequestMethod.PUT, path="", consumes="application/json")
    public UpdatePuzzleResponse updatePuzzle(@RequestBody UpdatePuzzleV2Request request) {
        return entityMapper.map(
                puzzleManager.update(
                        entityMapper.map(request, Puzzle.class))
                , UpdatePuzzleResponse.class);

    }

    @RequestMapping(method=RequestMethod.PATCH, path="", consumes="application/json")
    public UpdatePuzzleResponse patchPuzzle(@RequestBody UpdatePuzzleV2Request request) {
        return entityMapper.map(
                puzzleManager.patch(
                        entityMapper.map(request, Puzzle.class))
                , UpdatePuzzleResponse.class);
    }

    @RequestMapping(method=RequestMethod.POST, path="/{id}/board/verify")
    public VerifyBoardAnswerResponse verifyBoardAnswer(@PathVariable String id, @RequestBody VerifyBoardV2AnswerRequest request) {
        return new VerifyBoardAnswerResponse(answerManager.verifyBoardAnswer(request.getAnswer().stream().map(
                row -> row.stream().map(Box::getValue).collect(Collectors.toList())).collect(Collectors.toList()), id));
    }

}
