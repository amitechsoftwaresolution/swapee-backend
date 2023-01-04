package io.swapee.swapeebackend.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Minoltan Issack on 12/30/2022
 */
@Configuration
public class ProjectConfig {

    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .build();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

}
