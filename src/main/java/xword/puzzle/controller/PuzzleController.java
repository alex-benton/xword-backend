package xword.puzzle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xword.puzzle.controller.beans.*;
import xword.puzzle.manager.AnswerManager;
import xword.puzzle.objects.Direction;
import xword.util.EntityMapper;
import xword.puzzle.manager.PuzzleManager;
import xword.puzzle.objects.Puzzle;

/**
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/puzzle")
public class PuzzleController {

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private PuzzleManager puzzleManager;

    @Autowired
    private AnswerManager answerManager;

    // puzzle CRUD endpoints

    @RequestMapping(method=RequestMethod.GET, path="/{id}")
    public GetPuzzleByIdResponse getPuzzleById(@PathVariable String id) {
        return entityMapper.map(puzzleManager.get(id), GetPuzzleByIdResponse.class);
    }

    @RequestMapping(method=RequestMethod.GET, path="/edit/{editId}")
    public GetPuzzleByEditIdResponse getPuzzleByEditId(@PathVariable String editId) {
        return entityMapper.map(puzzleManager.getByEditId(editId), GetPuzzleByEditIdResponse.class);
    }

    @RequestMapping(method=RequestMethod.POST, path="", consumes="application/json")
    public CreatePuzzleResponse createPuzzle(@RequestBody CreatePuzzleRequest request) {
        return entityMapper.map(
                puzzleManager.save(
                        entityMapper.map(request, Puzzle.class))
                , CreatePuzzleResponse.class);
    }

    @RequestMapping(method=RequestMethod.PUT, path="", consumes="application/json")
    public UpdatePuzzleResponse updatePuzzle(@RequestBody UpdatePuzzleRequest request) {
        return entityMapper.map(
                puzzleManager.update(
                        entityMapper.map(request, Puzzle.class))
                , UpdatePuzzleResponse.class);

    }

    @RequestMapping(method=RequestMethod.PATCH, path="", consumes="application/json")
    public UpdatePuzzleResponse patchPuzzle(@RequestBody UpdatePuzzleRequest request) {
        return entityMapper.map(
                puzzleManager.patch(
                        entityMapper.map(request, Puzzle.class))
                , UpdatePuzzleResponse.class);
    }


    // answer GET endpoints

    @RequestMapping(method=RequestMethod.GET, path="/{id}/board/answer")
    public GetBoardAnswerResponse getBoardAnswer(@PathVariable String id) {
        return new GetBoardAnswerResponse(answerManager.getBoardAnswer(id));
    }

    @RequestMapping(method=RequestMethod.GET, path="/{id}/clue/answer")
    public GetClueAnswerResponse getClueAnswer(@PathVariable String id, @RequestParam("direction") String direction, @RequestParam("number") Integer number) {
        GetClueAnswerRequest request = new GetClueAnswerRequest();
        request.setDirection(Direction.fromString(direction));
        request.setNumber(number);
        return new GetClueAnswerResponse(answerManager.getClueAnswer(request.getNumber(), request.getDirection(), id));
    }

    @RequestMapping(method=RequestMethod.GET, path="/{id}/char/answer")
    public GetCharAnswerResponse getCharAnswer(@PathVariable String id, @RequestParam("x") Integer x, @RequestParam("y") Integer y) {
        GetCharAnswerRequest request = new GetCharAnswerRequest();
        request.setX(x);
        request.setY(y);
        return new GetCharAnswerResponse(answerManager.getCharacterAnswer(request.getX(), request.getY(), id));
    }

    // verify POST endpoints

    @RequestMapping(method=RequestMethod.POST, path="/{id}/board/verify")
    public VerifyBoardAnswerResponse verifyBoardAnswer(@PathVariable String id, @RequestBody VerifyBoardAnswerRequest request) {
        return new VerifyBoardAnswerResponse(answerManager.verifyBoardAnswer(request.getAnswer(), id));
    }

    @RequestMapping(method=RequestMethod.POST, path="/{id}/clue/verify")
    public VerifyClueAnswerResponse verifyClueAnswer(@PathVariable String id, @RequestBody VerifyClueAnswerRequest request) {
        return new VerifyClueAnswerResponse(answerManager.verifyClueAnswer(request.getAnswer(), request.getNumber(), request.getDirection(), id));
    }

    @RequestMapping(method=RequestMethod.POST, path="/{id}/char/verify")
    public VerifyCharAnswerResponse verifyCharAnswer(@PathVariable String id, @RequestBody VerifyCharAnswerRequest request) {
        return new VerifyCharAnswerResponse(answerManager.verifyCharacterAnswer(request.getCharacter(), request.getX(),request.getY(), id));
    }

}
