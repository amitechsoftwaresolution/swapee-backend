package io.swapee.swapeebackend.service_impl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import io.swapee.swapeebackend.service.FirebaseAuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class FirebaseAuthenticationServiceImpl implements FirebaseAuthenticationService {

    public FirebaseToken authenticateUser(String idToken) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().verifyIdToken(idToken);
    }
}

