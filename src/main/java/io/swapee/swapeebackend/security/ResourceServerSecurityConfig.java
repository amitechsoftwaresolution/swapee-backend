package io.swapee.swapeebackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/api/v1/user/**").permitAll()
                .antMatchers("/*", "/api/open").permitAll()
                .antMatchers("/api/v1/user-management/**").hasAuthority("ADMIN")
                .antMatchers("/api/v1/user-management/**").hasAuthority("STAFF")
                .antMatchers("/api/vendor").hasAuthority("VENDOR")
                .antMatchers(HttpMethod.POST, "/api/v1/user/dashboard/registration").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().disable()
                .csrf().disable()
                .addFilterBefore(new FirebaseTokenFilter(), UsernamePasswordAuthenticationFilter.class);;

        return http.build();
    }

}




