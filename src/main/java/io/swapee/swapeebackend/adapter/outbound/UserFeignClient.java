package io.swapee.swapeebackend.adapter.outbound;


import io.swapee.swapeebackend.common_library.resource.UserResource;
import io.swapee.swapeebackend.model.UserResponse;
import io.swapee.swapeebackend.proxy.UserFeignClientProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Minoltan Issack on 12/30/2022
 */

@RestController
@RequestMapping("/api/v1/user/feign-client")
public class UserFeignClient {

    private final UserFeignClientProxy userFeignClientProxy;

    public UserFeignClient(UserFeignClientProxy userFeignClientProxy) {
        this.userFeignClientProxy = userFeignClientProxy;
    }

    @PostMapping()
    public UserResponse createUser(@RequestBody UserResource user) {
        return userFeignClientProxy.createInternalUser(user);
    }
}
