package io.swapee.swapeebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableFeignClients
public class SwapeeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwapeeBackendApplication.class, args);

		// TODO integrate with pipeline
		// TODO implement integration test
		// TODO connect with firebase client for authentication module
		// TODO implement transaction feature
		// TODO implement function interface, class::method

	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
