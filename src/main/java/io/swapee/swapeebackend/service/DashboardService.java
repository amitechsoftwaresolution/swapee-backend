package io.swapee.swapeebackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author Minoltan Issack on 1/4/2023
 */
public interface DashboardService {

    void registerUser(String type,Object object, String platform) throws JsonProcessingException;
}
