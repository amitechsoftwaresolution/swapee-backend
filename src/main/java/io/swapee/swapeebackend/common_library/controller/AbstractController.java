package io.swapee.swapeebackend.common_library.controller;

import io.swapee.swapeebackend.common_library.reponse.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

/**
 * @author Minoltan Issack on 5/14/2022
 */
public abstract class AbstractController {
    HashMap<Integer, String> codeStatusMap = new HashMap<>();

    protected AbstractController() {
        codeStatusMap.put(200, "OK");
        codeStatusMap.put(201, "Created");
        codeStatusMap.put(204, "No Content");
        codeStatusMap.put(400, "Bad Request");
        codeStatusMap.put(401, "Unauthorized");
        codeStatusMap.put(403, "Forbidden");
        codeStatusMap.put(404, "Not Found");
        codeStatusMap.put(500, "Server Error");
    }


    public ResponseEntity<Object> sendSuccessResponse(Object object) {
        return ResponseEntity.ok().body(new Response(object, codeStatusMap.get(200)));
    }

    public ResponseEntity<Object> sendCreatedResponse() {
        return new ResponseEntity<>(new Response(codeStatusMap.get(201)), HttpStatus.CREATED);
    }


    public ResponseEntity<Object> sendSuccessResponseWithHeader(String header, Object objectHeader, Object entity) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(header, objectHeader.toString());
        return ResponseEntity.ok().headers(headers).body(new Response(entity, codeStatusMap.get(200)));
    }

    public ResponseEntity<Object> sendSuccessResponseWithHeaderNoContent(String header, Object objectHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(header, objectHeader.toString());
        return ResponseEntity.noContent().headers(headers).build();
    }

    public ResponseEntity<Object> handleServiceException(Exception e) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrorMessage(e.getMessage());
        response.setRequestedURI(null);
        response.setStatusInfo(codeStatusMap.get(500));

        return ResponseEntity.badRequest().body(response);


    }

    public ResponseEntity<Object> sendUnAuthenticationResponse(Exception e) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Subject", "Authentication Fail.");
        return ResponseEntity.badRequest().body(new Response(e.getMessage(), codeStatusMap.get(401)));
    }

}
