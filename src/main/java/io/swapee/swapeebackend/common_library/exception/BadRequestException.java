package io.swapee.swapeebackend.common_library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Minoltan Issack on 5/14/2022
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(Exception exception){
        super(exception);
    }

    public BadRequestException(String message, Exception e){
        super(message,e);
    }



}
