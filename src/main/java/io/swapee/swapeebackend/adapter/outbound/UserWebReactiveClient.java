package io.swapee.swapeebackend.adapter.outbound;

import io.swapee.swapeebackend.common_library.resource.UserResource;
import io.swapee.swapeebackend.model.UserResponse;
import io.swapee.swapeebackend.proxy.UserWebClientProxyReactive;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Minoltan Issack on 12/31/2022
 */
@RestController
@RequestMapping("/api/v1/user/web-client")
public class UserWebReactiveClient {

    private final UserWebClientProxyReactive userWebClientProxyReactive;

    public UserWebReactiveClient(UserWebClientProxyReactive userWebClientProxyReactive) {
        this.userWebClientProxyReactive = userWebClientProxyReactive;
    }

    @PostMapping()
    public Mono<UserResponse> createInternalUserReactive(@RequestBody UserResource user) {
        return userWebClientProxyReactive.createInternalUserReactive(user);
    }
}


