package io.swapee.swapeebackend.commonLibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Minoltan Issack on 5/14/2022
 */
@ResponseStatus(value= HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Exception exception) {
        super(exception);
    }

    public AuthenticationException(String message, Exception e) {
        super(message, e);
    }
}
