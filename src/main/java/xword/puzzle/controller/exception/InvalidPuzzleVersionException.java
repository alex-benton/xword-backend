package xword.puzzle.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for when a Puzzle can't be returned because the wrong endpoint was called.
 *
 * Maps to a 404 error code.
 *
 * @author alex
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Use the v2 endpoint to access this Puzzle")
public class InvalidPuzzleVersionException extends Exception {

}
