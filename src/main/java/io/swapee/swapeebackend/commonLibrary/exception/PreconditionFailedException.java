package io.swapee.swapeebackend.commonLibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Minoltan Issack on 5/14/2022
 */

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class PreconditionFailedException extends RuntimeException {
    public PreconditionFailedException(String message) {
        super(message);
    }

    public PreconditionFailedException(Exception exception) {
        super(exception);
    }

    public PreconditionFailedException(String message, Exception e) {
        super(message, e);
    }
}
