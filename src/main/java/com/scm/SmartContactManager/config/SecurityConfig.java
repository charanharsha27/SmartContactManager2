package com.scm.SmartContactManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService() {

    UserDetails user1 = User
    .withDefaultPasswordEncoder()
    .username("charan")
    .password("charan")
    .roles("ADMIN", "USER")
    .build();

    return  new InMemoryUserDetailsManager(user1);

    }
}