package xword.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Indicates that a request was invalid due to bad user input.
 *
 * @author alex
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {

    /**
     * Create a InvalidInputException with custom reason.
     *
     * @param reason the reason why we threw this exception
     */
    public InvalidInputException(String reason) {
        super(reason);
    }
}
