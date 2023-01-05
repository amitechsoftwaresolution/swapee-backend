package io.swapee.swapeebackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

/**
 * @author Minoltan Issack on 1/4/2023
 */
public interface UserManagementService {

    public void registerUser(String type,Object object) throws JsonProcessingException;
}
