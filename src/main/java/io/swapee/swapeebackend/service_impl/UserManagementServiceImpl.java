package io.swapee.swapeebackend.service_impl;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author Minoltan Issack on 8/14/2022
 */

@Service
public class UserManagementServiceImpl implements UserManagementService {
    private final Logger logger = Logger.getLogger(UserManagementServiceImpl.class.getName());
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private FirebaseAuth firebaseAuth;

    private final UserRepository userRepository;

    private static final String VIEWER = "VIEWER";

    private static final String GOOGLE_USER = "GOOGLE_USER";
    private static final String NORMAL_USER = "NORMAL_USER";

    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String ENCRYPTION_KEY = "swapeeEncryption";
    private static final String IV = "aesEncryptionKey";



    public UserManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public void registerUser(UserResource userResource) throws FirebaseAuthException {
        switch (userResource.getIdToken() != null ? GOOGLE_USER : NORMAL_USER) {
            case GOOGLE_USER: {
                userResource = getUserFromFirebase(userResource.getIdToken());
                userResource.setType(GOOGLE_USER);
                userResource.setRole(VIEWER);
                break;
            }
            case NORMAL_USER: {
                userResource.setType(NORMAL_USER);
                handleNormalUserRegistration(userResource);
                break;
            }
            default:
                throw new NotFoundException("Type not found");
        }

        saveUser(userResource);
    }

    private void handleNormalUserRegistration(UserResource userResource) throws FirebaseAuthException {
        String decryptedPassword = decryptPassword(userResource.getPassword());
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                .setEmail(userResource.getEmail())
                .setPassword(decryptedPassword);
        if (userResource.getRole() != null) {
            UserRecord userRecord = firebaseAuth.createUser(createRequest);
            setCustomClaims(userRecord, userResource.getRole());
        }
    }

    private void setCustomClaims(UserRecord userRecord, String role) throws FirebaseAuthException {
        // Set custom claims for the user
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        firebaseAuth.setCustomUserClaims(userRecord.getUid(), claims);
    }

    public UserResource getUserFromFirebase(String idToken)  {
        FirebaseToken token;
        try {
            token = firebaseAuth.verifyIdToken(idToken);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
        UserRecord userRecord;
        try {
            userRecord = firebaseAuth.getUser(token.getUid());
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
        UserResource userResource = new UserResource();
        userResource.setEmail(userRecord.getEmail());
        userResource.setName(userRecord.getDisplayName());
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
        if(checkUserExist(userResource.getEmail()))
            throw new ConflictException("User already exists");

        User user = new User();
        userResource.setStatus(userResource.getRole().equalsIgnoreCase(VIEWER));
        userResource.setCreatedAt(LocalDateTime.now());
        userResource.setUuid(UUID.randomUUID().toString());
        BeanUtils.copyProperties(userResource, user);
        userRepository.save(user);
    }

    public boolean checkUserExist(String email){
        return userRepository.existsByEmail(email);
    }

    public String decryptPassword(String encryptedPassword) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8), ENCRYPTION_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println("Decryption failed: " + e.getMessage());
            logger.info("Decryption failed: " + e.getMessage());
            return null;
        }
    }

}
