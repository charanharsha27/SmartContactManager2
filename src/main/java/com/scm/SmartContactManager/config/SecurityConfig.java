package com.scm.SmartContactManager.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.scm.SmartContactManager.service.UserDetailsServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
public class SecurityConfig {


    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private GoogleOAuth2AuthenticationSuccessHandler googleOAuth2AuthenticationSuccessHandler;

    @Bean
    public AuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsServiceImpl);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //configuring the url's

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests( (authorize) -> {
                        authorize
                                .requestMatchers("/index","/css/**","/js/**","/images/**","/signup","/login","/services","/contact","/about","/process-register").permitAll()
                                .requestMatchers("/user/**").authenticated();
            });
        
        http    .csrf( (csrf) -> {
                    csrf.disable();
                })
                .formLogin( (formLogin) -> {
                    formLogin.loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/user/dashboard",true)
                    .failureUrl("/login?error=true")
                    .usernameParameter("email")
                    .passwordParameter("password");
                });

        http
                .logout( (logout) -> {
                    logout.permitAll()
                    .logoutSuccessUrl("/login?logout=true");
                });
        
        http.oauth2Login((oauth2) -> {
            oauth2.loginPage("/login");
            oauth2.defaultSuccessUrl("/user/dashboard");
            oauth2.failureUrl("/login?error=true");
            oauth2.successHandler(googleOAuth2AuthenticationSuccessHandler);
        });

        return http.build();
    }
}