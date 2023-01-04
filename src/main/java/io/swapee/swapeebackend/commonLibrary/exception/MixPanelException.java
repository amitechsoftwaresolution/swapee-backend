package io.swapee.swapeebackend.commonLibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Minoltan Issack on 5/14/2022
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class MixPanelException extends RuntimeException {

    public MixPanelException(String message) {
        super(message);
    }

    public MixPanelException(Exception exception) {
        super(exception);
    }

    public MixPanelException(String message, Exception e) {
        super(message, e);
    }
}



