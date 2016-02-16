package xword.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Indicates that an exception occurred when attempting to map entities using EntityMapper.
 *
 * @author alex
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EntityMappingException extends RuntimeException {

    /**
     * Create a EntityMappingException with custom reason.
     *
     * @param reason the reason why we threw this exception
     */
    public EntityMappingException(String reason) {
        super(reason);
    }

}
