package io.swapee.swapeebackend.exception;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swapee.swapeebackend.commonLibrary.exception.*;
import io.swapee.swapeebackend.commonLibrary.reponse.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * @author Minoltan Issack on 5/14/2022
 */
public class RestTemplateCustomErrorHandler extends DefaultResponseErrorHandler {

    private static final Log logger = LogFactory.getLog(RestTemplateCustomErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse clienthttpresponse) throws IOException, BadRequestException,
            PortalException, AuthenticationException {
        String clientException = new String(getResponseBody(clienthttpresponse));
        Response response = new ObjectMapper().readValue(clientException, Response.class);

        switch (clienthttpresponse.getStatusCode()) {
            case BAD_REQUEST:
                throw new BadRequestException(response.getErrorMessage());
            case UNAUTHORIZED:
                throw new AuthenticationException(response.getErrorMessage());
            case FORBIDDEN:
                throw new ForbiddenException(response.getErrorMessage());
            case PRECONDITION_FAILED:
                throw new PreconditionFailedException(response.getErrorMessage());
            case NOT_FOUND:
                throw new NotFoundException(response.getErrorMessage());
            default:
                throw new PortalException(response.getErrorMessage());
        }
    }

}