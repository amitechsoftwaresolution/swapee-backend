package io.swapee.swapeebackend.adapter.inbound;

import io.swapee.swapeebackend.aspect.ToLog;
import io.swapee.swapeebackend.commonLibrary.controller.AbstractController;
import io.swapee.swapeebackend.model.User;
import io.swapee.swapeebackend.service.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Minoltan Issack on 8/14/2022
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserManagementController extends AbstractController {

    private final UserManagementService userManagementService;


    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping("/{name}")
    public List<User> save(@PathVariable String name){
        return userManagementService.test(name);
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
