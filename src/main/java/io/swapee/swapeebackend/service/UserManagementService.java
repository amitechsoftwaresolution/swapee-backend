package io.swapee.swapeebackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.auth.FirebaseAuthException;
import io.swapee.swapeebackend.common_library.resource.UserResource;

import java.util.List;

/**
 * @author Minoltan Issack on 1/4/2023
 */
public interface UserManagementService {

    void registerUser(UserResource userResource) throws JsonProcessingException, FirebaseAuthException;

    List<UserResource> getAllUsers();

    UserResource getUserById();

    void deleteUser(String uuid);

    UserResource getUserFromFirebase(String idToken) throws FirebaseAuthException;

    boolean checkUserExist(String email);
}
