package io.swapee.swapeebackend.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swapee.swapeebackend.common_library.exception.*;
import io.swapee.swapeebackend.common_library.reponse.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Minoltan Issack on 5/14/2022
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Response handleBadRequest(final BadRequestException exception,
                              final HttpServletRequest request) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(exception.getMessage());
        response.setRequestedURI(request.getRequestURI());
        response.setStatusInfo(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return response;
    }


    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public @ResponseBody
    Response handleUnauthorizedRequest(final AuthenticationException exception,
                                       final HttpServletRequest request) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(exception.getMessage());
        response.setRequestedURI(request.getRequestURI());
        response.setStatusInfo(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        return response;
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public @ResponseBody
    Response handleForbiddenExceptions(final ForbiddenException exception,
                                       final HttpServletRequest request) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(exception.getMessage());
        response.setRequestedURI(request.getRequestURI());
        response.setStatusInfo(HttpStatus.FORBIDDEN.getReasonPhrase());
        return response;
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Response handleMethodArgumentException(final MethodArgumentTypeMismatchException exception,
                                           final HttpServletRequest request) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(exception.getMessage());
        response.setRequestedURI(request.getRequestURI());
        response.setStatusInfo(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return response;
    }


    @ExceptionHandler(PortalException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Response handlePortalException(final PortalException exception,
                                   final HttpServletRequest request) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(exception.getMessage());
        response.setRequestedURI(request.getRequestURI());
        response.setStatusInfo(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return response;
    }

    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Response handleDatabaseException(final DatabaseException exception,
                                     final HttpServletRequest request) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(exception.getMessage());
        response.setRequestedURI(request.getRequestURI());
        response.setStatusInfo(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return response;
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody
    Response handleConflictException(final ConflictException exception,
                                     final HttpServletRequest request) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(exception.getMessage());
        response.setRequestedURI(request.getRequestURI());
        response.setStatusInfo(HttpStatus.CONFLICT.getReasonPhrase());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody Response handleOtherExceptions(final Exception exception,
                                                        final HttpServletRequest request) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(exception.getMessage());
        response.setRequestedURI(request.getRequestURI());
        response.setStatusInfo(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return response;
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Response handleException(final HttpClientErrorException exception,
                             final HttpServletRequest request) throws IOException, BadRequestException {
        Response oldException = new ObjectMapper().readValue(exception.getResponseBodyAsString(), Response.class);
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(oldException.getErrorMessage());
        response.setRequestedURI(request.getRequestURI());
        response.setStatusInfo(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Response handleMethodArgumentNotValidExceptionExceptions(final MethodArgumentNotValidException exception,
                                                             final HttpServletRequest request) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
        response.setRequestedURI(request.getRequestURI());
        response.setStatusInfo(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return response;
    }


    @ExceptionHandler(PreconditionFailedException.class)
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    public @ResponseBody
    Response handlePreconditionFailedException(final PreconditionFailedException exception,
                                               final HttpServletRequest request) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(exception.getMessage());
        response.setRequestedURI(request.getRequestURI());
        response.setStatusInfo(HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
        return response;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    Response handleNotFoundException(final NotFoundException exception,
                                     final HttpServletRequest request) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(exception.getMessage());
        response.setRequestedURI(request.getRequestURI());
        response.setStatusInfo(HttpStatus.NOT_FOUND.getReasonPhrase());
        return response;
    }

}

