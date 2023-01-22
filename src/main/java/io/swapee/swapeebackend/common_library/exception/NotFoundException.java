package io.swapee.swapeebackend.common_library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Minoltan Issack on 5/14/2022
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Exception exception) {
        super(exception);
    }

    public NotFoundException(String message, Exception e) {
        super(message, e);
    }
}
