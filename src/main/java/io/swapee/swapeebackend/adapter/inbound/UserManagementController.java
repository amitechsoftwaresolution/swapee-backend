package io.swapee.swapeebackend.adapter.inbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swapee.swapeebackend.aspect.ToLog;
import io.swapee.swapeebackend.commonLibrary.controller.AbstractController;
import io.swapee.swapeebackend.model.User;
import io.swapee.swapeebackend.service.UserManagementService;
import io.swapee.swapeebackend.serviceImpl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserManagementServiceImpl userManagementServiceImpl;


    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> registerUser(@RequestHeader String type, @RequestBody Object object) throws JsonProcessingException {
        userManagementService.registerUser(type,object);
        return sendCreatedResponse();
    }




    @PostMapping("/{name}")
    public List<User> save(@PathVariable String name){
        return userManagementServiceImpl.test(name);
    }


    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        return sendSuccessResponse(userManagementServiceImpl.getAllUsers());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getUserByUUID(@PathVariable String uuid){
        return sendSuccessResponse(userManagementServiceImpl.getUserById());
    }

    @DeleteMapping("/{uuid}")
    @ToLog
    public void delete(@PathVariable() String uuid){
        userManagementServiceImpl.deleteUser(uuid);
    }
}
