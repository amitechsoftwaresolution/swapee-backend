package io.swapee.swapeebackend.service_impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.swapee.swapeebackend.common_library.exception.NotFoundException;
import io.swapee.swapeebackend.common_library.resource.UserResource;
import io.swapee.swapeebackend.model.StaffDetails;
import io.swapee.swapeebackend.model.User;
import io.swapee.swapeebackend.model.VendorDetails;
import io.swapee.swapeebackend.repository.UserRepository;
import io.swapee.swapeebackend.service.UserManagementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Minoltan Issack on 8/14/2022
 */

@Service
public class UserManagementServiceImpl implements UserManagementService {
    private Logger logger = Logger.getLogger(UserManagementServiceImpl.class.getName());
    ObjectMapper objectMapper = new ObjectMapper();

    private final UserRepository userRepository;

    private static final String VIEWER = "VIEWER";
    private static final String VENDOR = "VENDOR";
    private static final String STAFF = "STAFF";

    public UserManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public void registerUser(String type, Object object, String platform) throws JsonProcessingException {
        UserResource userResource;

        userResource = objectMapper.convertValue(object,UserResource.class);
        switch (type.toUpperCase()){
            case VIEWER:
                saveUser(userResource);
                break;
            case VENDOR:
                VendorDetails vendorDetails;
                vendorDetails = objectMapper.convertValue(object,VendorDetails.class);
                createVendorDetails(vendorDetails,platform);
                saveUser(userResource);
                break;
            case STAFF:
                StaffDetails staffDetails;
                staffDetails = objectMapper.convertValue(object,StaffDetails.class);
                createStaffDetails(staffDetails);
                saveUser(userResource);
                break;
            default: throw new NotFoundException("No such a type");
        }
    }

    @Override
    public List<UserResource> getAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> new UserResource()).collect(Collectors.toList());
    }

    @Override
    public UserResource getUserById(){
        User user = userRepository.findById(1L).orElseThrow(() -> new NotFoundException("User Not Found!"));
        return new UserResource();
    }

    @Override
    public void deleteUser(String uuid){
        logger.info("Comes to delete method: "+ uuid);
    }

    public void createStaffDetails(StaffDetails staffDetails){
        // TODO create table and save
    }

    public void createVendorDetails(VendorDetails vendorDetails, String platform){
        // TODO get platform from dashboard or web
        // TODO create table and save
    }

    public void saveUser(UserResource userResource){
        // TODO add transactional
        // TODO create user table and save
    }



}
