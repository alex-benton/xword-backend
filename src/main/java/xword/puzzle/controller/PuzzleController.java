package xword.puzzle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xword.puzzle.controller.beans.*;
import xword.puzzle.controller.exception.InvalidPuzzleVersionException;
import xword.puzzle.controller.exception.PuzzleNotFoundException;
import xword.puzzle.manager.AnswerManager;
import xword.puzzle.objects.Direction;
import xword.util.EntityMapper;
import xword.puzzle.manager.PuzzleManager;
import xword.puzzle.objects.Puzzle;

/**
 * API controller for puzzles.
 *
 * Contains routes to create and update crossword puzzle game objects. In addition, provides resources to get
 * and verify answers to crossword puzzles.
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

    //
    // Puzzle CRUD endpoints
    //

    /**
     * Get a Puzzle game object by id.
     *
     * In contrast to {@link PuzzleController#getPuzzleByEditId}, getting a puzzle by id obfuscates the values
     * in the Clues and Board. Use this endpoint when getting a Puzzle to solve.
     *
     * @param id the id of the puzzle to get
     * @return a response object containing the puzzle and related information
     * @throws PuzzleNotFoundException (404 error code) if the puzzle can't be found
     * @throws InvalidPuzzleVersionException (400 error code) if the puzzle was created with the V2 api
     * @deprecated  the data structure of Puzzle has changed to support more complicated puzzle types.
     *              Use {@link PuzzleV2Controller#getPuzzleV2ById(String)}.
     */
    @Deprecated
    @RequestMapping(method=RequestMethod.GET, path="/{id}")
    public GetPuzzleByIdResponse getPuzzleById(@PathVariable String id) throws PuzzleNotFoundException, InvalidPuzzleVersionException {
        Puzzle puzzle = puzzleManager.get(id);

        if (puzzle == null) {
            throw new PuzzleNotFoundException();
        }

        if (puzzle.getBoard() == null && puzzle.getBoardV2() != null) {
            throw new InvalidPuzzleVersionException();
        }

        return entityMapper.map(puzzle, GetPuzzleByIdResponse.class);
    }

    /**
     * Get a Puzzle game object by editId.
     *
     * In contrast to {@link PuzzleController#getPuzzleById}, getting a puzzle by editId contains all information
     * about the puzzle, including the solution. Use this endpoint when getting a Puzzle to edit.
     *
     * @param editId the editId of the puzzle to get
     * @return a response object containing the puzzle and related information
     * @throws PuzzleNotFoundException (404 error code) if the puzzle can't be found
     * @throws InvalidPuzzleVersionException (400 error code) if the puzzle was created with the V2 api
     * @deprecated  the data structure of Puzzle has changed to support more complicated puzzle types.
     *              Use {@link PuzzleV2Controller#getPuzzleV2ByEditId(String)}.
     */
    @Deprecated
    @RequestMapping(method=RequestMethod.GET, path="/edit/{editId}")
    public GetPuzzleByEditIdResponse getPuzzleByEditId(@PathVariable String editId) throws PuzzleNotFoundException, InvalidPuzzleVersionException {
        Puzzle puzzle = puzzleManager.getByEditId(editId);
        if (puzzle == null) {
            throw new PuzzleNotFoundException();
        }

        if (puzzle.getBoard() == null && puzzle.getBoardV2() != null) {
            throw new InvalidPuzzleVersionException();
        }

        return entityMapper.map(puzzle, GetPuzzleByEditIdResponse.class);
    }

    /**
     * Create a new puzzle.
     *
     * @param request the puzzle to create
     * @return a response containing the id and editId of the newly created puzzle
     * @deprecated  the data structure of Puzzle has changed to support more complicated puzzle types.
     *              Use {@link PuzzleV2Controller#createPuzzleV2(CreatePuzzleV2Request)}.
     */
    @Deprecated
    @RequestMapping(method=RequestMethod.POST, path="", consumes="application/json")
    public CreatePuzzleResponse createPuzzle(@RequestBody CreatePuzzleRequest request) {
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
     * @deprecated  the data structure of Puzzle has changed to support more complicated puzzle types.
     *              Use {@link PuzzleV2Controller#updatePuzzleV2(UpdatePuzzleV2Request)}.
     */
    @Deprecated
    @RequestMapping(method=RequestMethod.PUT, path="", consumes="application/json")
    public UpdatePuzzleResponse updatePuzzle(@RequestBody UpdatePuzzleRequest request) {
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
     * @deprecated  the data structure of Puzzle has changed to support more complicated puzzle types.
     *              Use {@link PuzzleV2Controller#patchPuzzleV2(UpdatePuzzleV2Request)}.
     */
    @Deprecated
    @RequestMapping(method=RequestMethod.PATCH, path="", consumes="application/json")
    public UpdatePuzzleResponse patchPuzzle(@RequestBody UpdatePuzzleRequest request) {
        return entityMapper.map(
                puzzleManager.patch(
                        entityMapper.map(request, Puzzle.class))
                , UpdatePuzzleResponse.class);
    }


    //
    // Answer GET endpoints.
    //

    /**
     * Get the solution to the board with provided id.
     *
     * @param id the id of the puzzle to find an answer for
     * @return the completed board
     */
    @RequestMapping(method=RequestMethod.GET, path="/{id}/board/answer")
    public GetBoardAnswerResponse getBoardAnswer(@PathVariable String id) {
        return new GetBoardAnswerResponse(answerManager.getBoardAnswer(id));
    }

    /**
     * Get the solution to the Clue with specified direction and number.
     *
     * @param id the id of the puzzle to find an answer for
     * @param direction the direction of the clue ('across' or 'down')
     * @param number the clue number
     * @return the answer for the clue with specified direction and number
     */
    @RequestMapping(method=RequestMethod.GET, path="/{id}/clue/answer")
    public GetClueAnswerResponse getClueAnswer(@PathVariable String id, @RequestParam("direction") String direction, @RequestParam("number") Integer number) {
        GetClueAnswerRequest request = new GetClueAnswerRequest(number, Direction.valueOf(direction));
        return new GetClueAnswerResponse(answerManager.getClueAnswer(request.getNumber(), request.getDirection(), id));
    }

    /**
     * Get the solution to the box at the specified x and y position on the board.
     *
     * @param id the id of the puzzle to find an answer for
     * @param x the x-position of the box
     * @param y the y-position of the box
     * @return the answer for the box at (x,y) on the board
     */
    @RequestMapping(method=RequestMethod.GET, path="/{id}/char/answer")
    public GetCharAnswerResponse getCharAnswer(@PathVariable String id, @RequestParam("x") Integer x, @RequestParam("y") Integer y) {
        GetCharAnswerRequest request = new GetCharAnswerRequest(x, y);
        return new GetCharAnswerResponse(answerManager.getCharacterAnswer(request.getX(), request.getY(), id));
    }

    //
    // Verify POST endpoints.
    //

    /**
     * Verify the solution to the puzzle with specified id.
     *
     * @param id the id of the puzzle to verify an answer for
     * @param request a request containing the 'guess'
     * @return a response indicating whether or not the 'guess' was correct
     */
    @RequestMapping(method=RequestMethod.POST, path="/{id}/board/verify")
    public VerifyBoardAnswerResponse verifyBoardAnswer(@PathVariable String id, @RequestBody VerifyBoardAnswerRequest request) {
        return new VerifyBoardAnswerResponse(answerManager.verifyBoardAnswer(request.getAnswer(), id));
    }

    /**
     * Verify the solution to the Clue with specified direction and number.
     *
     * @param id the id of the puzzle to verify an answer for
     * @param request a request containing the 'guess'
     * @return a response indicating whether or not the 'guess' was correct
     */
    @RequestMapping(method=RequestMethod.POST, path="/{id}/clue/verify")
    public VerifyClueAnswerResponse verifyClueAnswer(@PathVariable String id, @RequestBody VerifyClueAnswerRequest request) {
        return new VerifyClueAnswerResponse(answerManager.verifyClueAnswer(request.getAnswer(), request.getNumber(), request.getDirection(), id));
    }

    /**
     * Verify the solution to the box at the specified x and y position on the board.
     *
     * @param id the id of the puzzle to verify an answer for
     * @param request a request containing the 'guess'
     * @return a response indicating whether or not the 'guess' was correct
     */
    @RequestMapping(method=RequestMethod.POST, path="/{id}/char/verify")
    public VerifyCharAnswerResponse verifyCharAnswer(@PathVariable String id, @RequestBody VerifyCharAnswerRequest request) {
        return new VerifyCharAnswerResponse(answerManager.verifyCharacterAnswer(request.getCharacter(), request.getX(),request.getY(), id));
    }

}
