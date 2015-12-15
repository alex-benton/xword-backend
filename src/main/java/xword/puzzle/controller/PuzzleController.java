package xword.puzzle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xword.puzzle.controller.entities.GetPuzzleByEditIdResponse;
import xword.puzzle.controller.entities.GetPuzzleByIdResponse;
import xword.util.EntityMapper;
import xword.puzzle.controller.entities.CreatePuzzleRequest;
import xword.puzzle.controller.entities.CreatePuzzleResponse;
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

}
