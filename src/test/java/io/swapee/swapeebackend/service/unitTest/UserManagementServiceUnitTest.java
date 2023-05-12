package io.swapee.swapeebackend.service.unitTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swapee.swapeebackend.common_library.exception.NotFoundException;
import io.swapee.swapeebackend.repository.UserRepository;
import io.swapee.swapeebackend.service.UserManagementService;
import io.swapee.swapeebackend.service_impl.UserManagementServiceImpl;
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
class UserManagementServiceUnitTest {

    // https://www.baeldung.com/mockito-junit-5-extension

    private String webPlatform = "web";
    private String dashboardPlatform = "dashboard";

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserManagementService userManagementService = new UserManagementServiceImpl(userRepository);

    @Test
    @DisplayName("register viewer user happy path with web platform")
    void registerUserViewerSuccessTest() throws JsonProcessingException {
        userManagementService.registerUser("VIEWER", prepareCommonObject(),webPlatform);
    }

    @Test
    @DisplayName("register viewer user happy path with web platform")
    void registerUserViewerWithDashboardSuccessTest() throws JsonProcessingException {
        userManagementService.registerUser("VIEWER", prepareCommonObject(),dashboardPlatform);
    }

    @Test
    @DisplayName("register vendor user happy path")
    void registerUserVendorSuccessTest() throws JsonProcessingException {
        userManagementService.registerUser("VENDOR", prepareCommonObject(),"");
    }

    @Test
    @DisplayName("register staff user happy path")
    void registerUserStaffSuccessTest() throws JsonProcessingException {
        userManagementService.registerUser("STAFF", prepareCommonObject(),dashboardPlatform);
    }

    @Test
    @DisplayName("register user wrong type test")
    void registerUserWrongTypeFailTest() throws JsonProcessingException {
        assertThrows(NotFoundException.class, ()-> userManagementService.registerUser("wrong", prepareCommonObject(), ""));
    }

    private Object prepareCommonObject(){
        return new Object();
    }
}
