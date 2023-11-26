package io.swapee.swapeebackend.adapter.inbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import io.swapee.swapeebackend.common_library.controller.AbstractController;
import io.swapee.swapeebackend.common_library.resource.UserResource;
import io.swapee.swapeebackend.model.User;
import io.swapee.swapeebackend.repository.UserRepository;
import io.swapee.swapeebackend.service.UserManagementService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class AuthController extends AbstractController {

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Autowired
    UserManagementService userManagementService;

    @PostMapping("/registration")
    public ResponseEntity<Object> registerUser(@RequestBody UserResource userResource) throws JsonProcessingException, FirebaseAuthException {
        userManagementService.registerUser(userResource);
        return sendCreatedResponse();
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody String idToken) {
        try {
            FirebaseToken token = firebaseAuth.verifyIdToken(idToken);

            // Get the user's custom claims to determine their role
            Map<String, Object> customClaims = token.getClaims();
            String userRole = (String) customClaims.get("role");

            return "Login successful for user with UID: " + token.getUid() + ", Role: " + userRole;
        } catch (FirebaseAuthException e) {
            return "Authentication failed: " + e.getMessage();
        }
    }
}
