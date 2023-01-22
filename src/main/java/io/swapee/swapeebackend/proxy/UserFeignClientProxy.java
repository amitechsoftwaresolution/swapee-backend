package io.swapee.swapeebackend.proxy;

import io.swapee.swapeebackend.common_library.resource.UserResource;
import io.swapee.swapeebackend.config.ProjectConfig;
import io.swapee.swapeebackend.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Minoltan Issack on 12/30/2022
 */
@FeignClient(name = "userFeign", configuration = ProjectConfig.class, url = "${user.service.url}")
public interface UserFeignClientProxy {

    // https://www.baeldung.com/spring-cloud-openfeign

    // Logging aspect does not intercept feign client, so we used to feign logging set in the app properties,
    // and this logging is only works in debug level
    @PostMapping("/api/users")
    UserResponse createInternalUser(@RequestBody UserResource user);
}
