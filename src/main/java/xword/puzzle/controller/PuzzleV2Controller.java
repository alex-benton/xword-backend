package xword.puzzle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xword.puzzle.controller.beans.*;
import xword.puzzle.controller.exception.PuzzleNotFoundException;
import xword.puzzle.manager.AnswerManager;
import xword.puzzle.manager.PuzzleManager;
import xword.puzzle.objects.Puzzle;
import xword.puzzle.util.PuzzleHelper;
import xword.util.EntityMapper;

/**
 * V2 API controller for puzzles.
 *
 * Contains routes to create and update crossword puzzle game objects. In addition, provides resources to get
 * and verify answers to crossword puzzles.
 *
 * The data structure for puzzle game boards changed, requiring a change in request format. The V2 api handles
 * requests and responses with the new board structure.
 *
 * V2 requests are backwards-compatible (requesting a puzzle saved in the V1 format will convert the puzzle
 * to V2 format in the response).
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


    /**
     * Get a Puzzle game object by id.
     *
     * In contrast to {@link PuzzleV2Controller#getPuzzleV2ByEditId}, getting a puzzle by id obfuscates the values
     * in the Clues and Board. Use this endpoint when getting a Puzzle to solve.
     *
     * @param id the id of the puzzle to get
     * @return a response object containing the puzzle and related information
     * @throws PuzzleNotFoundException (404 error code) if the puzzle can't be found
     */
    @RequestMapping(method= RequestMethod.GET, path="/{id}")
    public GetPuzzleV2ByIdResponse getPuzzleV2ById(@PathVariable String id) throws PuzzleNotFoundException {
        Puzzle puzzle = puzzleManager.get(id);
        if (puzzle == null) {
            throw new PuzzleNotFoundException();
        }
        return entityMapper.map(puzzle, GetPuzzleV2ByIdResponse.class);
    }

    /**
     * Get a Puzzle game object by editId.
     *
     * In contrast to {@link PuzzleV2Controller#getPuzzleV2ById}, getting a puzzle by editId contains all information
     * about the puzzle, including the solution. Use this endpoint when getting a Puzzle to edit.
     *
     * @param editId the editId of the puzzle to get
     * @return a response object containing the puzzle and related information
     * @throws PuzzleNotFoundException (404 error code) if the puzzle can't be found
     */
    @RequestMapping(method=RequestMethod.GET, path="/edit/{editId}")
    public GetPuzzleV2ByEditIdResponse getPuzzleV2ByEditId(@PathVariable String editId) throws PuzzleNotFoundException {
        Puzzle puzzle = puzzleManager.getByEditId(editId);
        if (puzzle == null) {
            throw new PuzzleNotFoundException();
        }
        return entityMapper.map(puzzle, GetPuzzleV2ByEditIdResponse.class);
    }

    /**
     * Create a new puzzle.
     *
     * @param request the puzzle to create
     * @return a response containing the id and editId of the newly created puzzle
     */
    @RequestMapping(method=RequestMethod.POST, path="", consumes="application/json")
    public CreatePuzzleResponse createPuzzleV2(@RequestBody CreatePuzzleV2Request request) {
        return entityMapper.map(
                puzzleManager.save(
                        entityMapper.map(request, Puzzle.class))
                , CreatePuzzleResponse.class);
    }

    /**
     * Update an existing puzzle.
     *
     * @param request the puzzle to update
     * @return a response containing the id and editId of the updated puzzle
     */
    @RequestMapping(method=RequestMethod.PUT, path="", consumes="application/json")
    public UpdatePuzzleResponse updatePuzzleV2(@RequestBody UpdatePuzzleV2Request request) {
        return entityMapper.map(
                puzzleManager.update(
                        entityMapper.map(request, Puzzle.class))
                , UpdatePuzzleResponse.class);

    }

    /**
     * Update an existing puzzle. Replaces fields that are present in the provided UpdatePuzzleRequest
     * and ignores fields that aren't.
     *
     * @param request the puzzle to patch
     * @return a response containing the id and editId of the updated puzzle
     */
    @RequestMapping(method=RequestMethod.PATCH, path="", consumes="application/json")
    public UpdatePuzzleResponse patchPuzzleV2(@RequestBody UpdatePuzzleV2Request request) {
        return entityMapper.map(
                puzzleManager.patch(
                        entityMapper.map(request, Puzzle.class))
                , UpdatePuzzleResponse.class);
    }

    /**
     * Verify the solution to the puzzle with specified id.
     *
     * @param id the id of the puzzle to verify an answer for
     * @param request a request containing the 'guess,' in V2 format
     * @return a response indicating whether or not the 'guess' was correct
     */
    @RequestMapping(method=RequestMethod.POST, path="/{id}/board/verify")
    public VerifyBoardAnswerResponse verifyBoardV2Answer(@PathVariable String id, @RequestBody VerifyBoardV2AnswerRequest request) {
        return new VerifyBoardAnswerResponse(answerManager.verifyBoardAnswer(PuzzleHelper.convertV2Board(request.getAnswer()), id));
    }

}
