package xword.puzzle.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for when a Puzzle can't be found.
 *
 * Maps to a 404 error code.
 *
 * @author alex
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="The specified puzzle was not found.")
public class PuzzleNotFoundException extends RuntimeException {

    /**
     * Create a PuzzleNotFoundException with default reason.
     */
    public PuzzleNotFoundException() {
        super();
    }

    /**
     * Create a PuzzleNotFoundException with custom reason.
     *
     * @param reason the reason why we threw this exception
     */
    public PuzzleNotFoundException(String reason) {
        super(reason);
    }
}
