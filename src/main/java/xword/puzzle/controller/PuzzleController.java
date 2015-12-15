package xword.puzzle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public Puzzle getPuzzleById(@PathVariable String id) {
        return null;
    }

    @RequestMapping(method=RequestMethod.GET, path="/edit/{editId}")
    public Puzzle getPuzzleByEditId(@PathVariable String editId) {
        return null;
    }

    @RequestMapping(method=RequestMethod.POST, path="", consumes="application/json")
    public CreatePuzzleResponse createPuzzle(@RequestBody CreatePuzzleRequest request) {
        return entityMapper.map(
                puzzleManager.save(
                        entityMapper.map(request, Puzzle.class))
                , CreatePuzzleResponse.class);
    }

}
