package io.swapee.swapeebackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swapee.swapeebackend.commonLibrary.exception.NotFoundException;
import io.swapee.swapeebackend.repository.UserRepository;
import io.swapee.swapeebackend.serviceImpl.UserManagementServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Minoltan Issack on 1/5/2023
 */
@ExtendWith(MockitoExtension.class)
public class UserManagementServiceTest {

    // https://www.baeldung.com/mockito-junit-5-extension

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserManagementService userManagementService = new UserManagementServiceImpl(userRepository);

    @Test
    @DisplayName("register normal user happy path")
    public void registerUserNormalSuccessTest() throws JsonProcessingException {
        userManagementService.registerUser("NORMAL", prepareCommonObject());
    }

    @Test
    @DisplayName("register vendor user happy path")
    public void registerUserVendorSuccessTest() throws JsonProcessingException {
        userManagementService.registerUser("VENDOR", prepareCommonObject());
    }

    @Test
    @DisplayName("register staff user happy path")
    public void registerUserStaffSuccessTest() throws JsonProcessingException {
        userManagementService.registerUser("STAFF", prepareCommonObject());
    }

    @Test
    @DisplayName("register user wrong type test")
    public void registerUserWrongTypeFailTest() throws JsonProcessingException {
        assertThrows(NotFoundException.class, ()-> userManagementService.registerUser("wrong", prepareCommonObject()));
    }

    private Object prepareCommonObject(){
        return new Object();
    }
}
