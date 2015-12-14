package xword.puzzle.controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import xword.puzzle.objects.Puzzle;

/**
 * Created by alex on 12/13/15.
 */
@RestController
@RequestMapping("/puzzle")
public class PuzzleController {

    @RequestMapping(method=RequestMethod.GET, path="/{id}")
    public Puzzle getPuzzleById(@PathVariable String id) {
        return null;
    }

    @RequestMapping(method=RequestMethod.GET, path="/edit/{editId}")
    public Puzzle getPuzzleByEditId(@PathVariable String editId) {
        return null;
    }

    @RequestMapping(method=RequestMethod.POST, path="/")
    public void createPuzzle(@RequestParam(name="puzzle") MultiValueMap<String, String> puzzle) {

    }

}
