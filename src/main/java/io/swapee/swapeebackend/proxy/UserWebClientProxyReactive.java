package io.swapee.swapeebackend.proxy;


import io.swapee.swapeebackend.common_library.resource.UserResource;
import io.swapee.swapeebackend.model.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Minoltan Issack on 12/31/2022
 */
@Component
public class UserWebClientProxyReactive {

    private final WebClient webClient;

    @Value("${user.service.url}")
    private String url;

    public UserWebClientProxyReactive(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<UserResponse> createInternalUserReactive(UserResource user) {
             return webClient.post()
                    .uri(url + "/api/users")
                    .body(Mono.just(user), UserResource.class)
                    .retrieve()
                    .bodyToMono(UserResponse.class);
    }
}


