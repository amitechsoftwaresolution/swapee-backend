package io.swapee.swapeebackend.service_impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swapee.swapeebackend.service.DashboardService;
import org.springframework.stereotype.Service;

/**
 * @author Minoltan Issack on 1/22/2023
 */
@Service
public class DashboardServiceImpl implements DashboardService {


    @Override
    public void registerUser(String type, Object object, String platform) throws JsonProcessingException {
        // TODO switch case as user management service impl
    }
}
