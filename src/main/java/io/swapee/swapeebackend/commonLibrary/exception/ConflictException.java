package io.swapee.swapeebackend.commonLibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Minoltan Issack on 5/14/2022
 */
@ResponseStatus(value= HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(Exception exception) {
        super(exception);
    }

    public ConflictException(String message, Exception e) {
        super(message, e);
    }
}
