package io.swapee.swapeebackend.adapter.inbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import io.swapee.swapeebackend.aspect.ToLog;
import io.swapee.swapeebackend.common_library.controller.AbstractController;
import io.swapee.swapeebackend.common_library.resource.UserResource;
import io.swapee.swapeebackend.service.UserManagementService;
import io.swapee.swapeebackend.service_impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Map;


/**
 * @author Minoltan Issack on 8/14/2022
 */
@RestController
@RequestMapping("/api/v1/user-management")
public class UserManagementController extends AbstractController {

    private final UserManagementService userManagementService;

    @Autowired
    UserManagementServiceImpl userManagementServiceImpl;


    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }


    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        return sendSuccessResponse(userManagementService.getAllUsers());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getUserByUUID(@PathVariable String uuid){
        return sendSuccessResponse(userManagementService.getUserById());
    }

    @DeleteMapping("/{uuid}")
    @ToLog
    public void delete(@PathVariable() String uuid){
        userManagementService.deleteUser(uuid);
    }
}
