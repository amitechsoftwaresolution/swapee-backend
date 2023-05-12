package io.swapee.swapeebackend.adapter.inbound.dashboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swapee.swapeebackend.common_library.controller.AbstractController;
import io.swapee.swapeebackend.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Minoltan Issack on 1/22/2023
 */
@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController extends AbstractController {


    private DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> registerUser(@RequestHeader(defaultValue = "dashboard") String platform, @RequestHeader String type, @RequestBody Object object) throws JsonProcessingException {
        dashboardService.registerUser(type,object,platform);
        return sendCreatedResponse();
    }
}
