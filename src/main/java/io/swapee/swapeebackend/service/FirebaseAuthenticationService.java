package io.swapee.swapeebackend.service;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

public interface FirebaseAuthenticationService {
    FirebaseToken authenticateUser(String idToken) throws FirebaseAuthException;
}

