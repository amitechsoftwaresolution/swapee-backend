package io.swapee.swapeebackend.commonLibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Minoltan Issack on 5/14/2022
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class PortalException extends RuntimeException {

    public PortalException(String message) {
        super(message);
    }

    public PortalException(Exception exception) {
        super(exception);
    }

    public PortalException(String message, Exception e) {
        super(message, e);
    }
}



