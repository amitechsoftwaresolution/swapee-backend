package io.swapee.swapeebackend.common_library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Minoltan Issack on 5/14/2022
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class DatabaseException extends RuntimeException{

    public DatabaseException(String message){
        super(message);
    }

    public DatabaseException(Exception exception){
        super(exception);
    }

    public DatabaseException(String message, Exception e){
        super(message,e);
    }


}
