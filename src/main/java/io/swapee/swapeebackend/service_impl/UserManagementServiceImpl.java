package io.swapee.swapeebackend.service_impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import io.swapee.swapeebackend.common_library.exception.ConflictException;
import io.swapee.swapeebackend.common_library.exception.NotFoundException;
import io.swapee.swapeebackend.common_library.resource.UserResource;
import io.swapee.swapeebackend.model.StaffDetails;
import io.swapee.swapeebackend.model.User;
import io.swapee.swapeebackend.model.VendorDetails;
import io.swapee.swapeebackend.repository.UserRepository;
import io.swapee.swapeebackend.service.UserManagementService;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Minoltan Issack on 8/14/2022
 */

@Service
public class UserManagementServiceImpl implements UserManagementService {
    private Logger logger = Logger.getLogger(UserManagementServiceImpl.class.getName());
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private FirebaseAuth firebaseAuth;

    private final UserRepository userRepository;

    private static final String VIEWER = "VIEWER";

    private static final String GOOGLE_USER = "GOOGLE_USER";
    private static final String NORMAL_USER = "NORMAL_USER";


    public UserManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public void registerUser(UserResource userResource) throws FirebaseAuthException {
        switch (userResource.getType()) {
            case GOOGLE_USER:
                handleGoogleUserRegistration(userResource);
                userResource = getUserFromFirebase(userResource.getIdToken());
                break;
            case NORMAL_USER:
                handleNormalUserRegistration(userResource);
                break;
            default:
                throw new NotFoundException("Type not found");
        }

        saveUser(userResource);
    }

    private void handleNormalUserRegistration(UserResource userResource) throws FirebaseAuthException {
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                .setEmail(userResource.getEmail())
                .setPassword(userResource.getPassword());
        UserRecord userRecord = firebaseAuth.createUser(createRequest);

        setCustomClaims(userRecord, userResource.getRole());
    }

    private void handleGoogleUserRegistration(UserResource userResource) throws FirebaseAuthException {
        FirebaseToken token = firebaseAuth.verifyIdToken(userResource.getIdToken());
        UserRecord userRecord = firebaseAuth.getUser(token.getUid());

        setCustomClaims(userRecord, userResource.getRole());
    }
    private void setCustomClaims(UserRecord userRecord, String role) throws FirebaseAuthException {
        // Set custom claims for the user
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        firebaseAuth.setCustomUserClaims(userRecord.getUid(), claims);
    }

    private UserResource getUserFromFirebase(String idToken) throws FirebaseAuthException {
        FirebaseToken token = firebaseAuth.verifyIdToken(idToken);
        UserRecord userRecord = firebaseAuth.getUser(token.getUid());
        UserResource userResource = new UserResource();
        userResource.setEmail(userRecord.getEmail());
        userResource.setName(userRecord.getDisplayName());
        userResource.setRole((String) userRecord.getCustomClaims().get("role"));
        return userResource;
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
        if(userRepository.existsByEmail(userResource.getEmail()))
            throw new ConflictException("User already exists");

        User user = new User();
        userResource.setStatus(userResource.getRole().equalsIgnoreCase(VIEWER));
        userResource.setCreatedAt(LocalDateTime.now());
        userResource.setUuid(UUID.randomUUID().toString());
        BeanUtils.copyProperties(userResource, user);
        userRepository.save(user);
    }



}
