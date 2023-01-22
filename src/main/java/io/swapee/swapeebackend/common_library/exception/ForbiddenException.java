package io.swapee.swapeebackend.common_library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Minoltan Issack on 5/14/2022
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Exception exception) {
        super(exception);
    }

    public ForbiddenException(String message, Exception e) {
        super(message, e);
    }
}
