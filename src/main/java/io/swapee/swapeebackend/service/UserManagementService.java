package io.swapee.swapeebackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swapee.swapeebackend.common_library.resource.UserResource;

import java.util.List;

/**
 * @author Minoltan Issack on 1/4/2023
 */
public interface UserManagementService {

    void registerUser(String type,Object object, String platform) throws JsonProcessingException;

    List<UserResource> getAllUsers();

    UserResource getUserById();

    void deleteUser(String uuid);
}
