package xword.puzzle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xword.puzzle.controller.entities.*;
import xword.puzzle.manager.AnswerManager;
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

    // answer GET endpoints

    @RequestMapping(method=RequestMethod.GET, path="/{id}/board/answer")
    public GetBoardAnswerResponse getBoardAnswer(@PathVariable String id) {
        return new GetBoardAnswerResponse(answerManager.getBoardAnswer(id));
    }

    @RequestMapping(method=RequestMethod.GET, path="/{id}/clue/answer")
    public GetClueAnswerResponse getClueAnswer(@PathVariable String id, @RequestBody GetClueAnswerRequest request) {
        return new GetClueAnswerResponse(answerManager.getClueAnswer(request.getNumber(), request.getDirection(), id));
    }

    @RequestMapping(method=RequestMethod.GET, path="/{id}/char/answer")
    public GetCharAnswerResponse getCharAnswer(@PathVariable String id, @RequestBody GetCharAnswerRequest request) {
        return new GetCharAnswerResponse(answerManager.getCharacterAnswer(request.getX(), request.getY(), id));
    }



}
