package io.swapee.swapeebackend.service;

import io.swapee.swapeebackend.commonLibrary.exception.NotFoundException;
import io.swapee.swapeebackend.commonLibrary.resource.UserResource;
import io.swapee.swapeebackend.model.User;
import io.swapee.swapeebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Minoltan Issack on 8/14/2022
 */

@Service
public class UserManagementService {
    private Logger logger = Logger.getLogger(UserManagementService.class.getName());

    private final UserRepository userRepository;

    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> test(String name){
        logger.info("coming to user management service");
        User user = new User();
        user.setName(name);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        return userList;
    }

    public List<UserResource> getAllUsers(){
       List<User> userList = userRepository.findAll();
       return userList.stream().map(user -> new UserResource(user.getName(), user.getJob())).collect(Collectors.toList());
    }

    public UserResource getUserById(){
        User user = userRepository.findById(1L).orElseThrow(() -> new NotFoundException("User Not Found!"));
        return new UserResource(user.getName(), user.getJob());
    }

    public void deleteUser(String uuid){
       logger.info("Comes to delete method: "+ uuid);
    }
}
