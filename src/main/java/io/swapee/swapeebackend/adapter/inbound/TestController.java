package io.swapee.swapeebackend.adapter.inbound;

import com.google.firebase.auth.FirebaseToken;
import io.swapee.swapeebackend.service.FirebaseAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private FirebaseAuthenticationService firebaseAuthenticationService;

    @GetMapping("/vendor")
    public String securedEndpoint(@RequestHeader("Authorization") String idToken) {
        try {
            FirebaseToken firebaseToken = firebaseAuthenticationService.authenticateUser(idToken);
            return "Hello, " + firebaseToken.getEmail();
        } catch (Exception e) {
            return "Authentication failed: " + e.getMessage();
        }
    }


    @GetMapping("/open")
    public String openEndpoint() {
        return "Hello, anonymous user!";
    }
}
