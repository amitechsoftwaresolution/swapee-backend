package io.swapee.swapeebackend.adapter.inbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.auth.FirebaseAuthException;
import io.swapee.swapeebackend.common_library.controller.AbstractController;
import io.swapee.swapeebackend.common_library.resource.UserResource;
import io.swapee.swapeebackend.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/user")
public class AuthController extends AbstractController {


    @Autowired
    UserManagementService userManagementService;

    @PostMapping("/registration")
    public ResponseEntity<Object> registerUser(@RequestBody UserResource userResource) throws JsonProcessingException, FirebaseAuthException {
        userManagementService.registerUser(userResource);
        return sendCreatedResponse();
    }

//    no need manual login from the backend, login handle from the firebase
    @PostMapping("/google/user/exist")
    public Boolean checkGoogleUserExist(@RequestBody String idToken) throws FirebaseAuthException, JsonProcessingException {
        UserResource userResource = userManagementService.getUserFromFirebase(idToken);
        userResource.setIdToken(idToken);
        if(!userManagementService.checkUserExist(userResource.getEmail())){
            userManagementService.registerUser(userResource);
            return true;
        }
        return true;
    }
}
