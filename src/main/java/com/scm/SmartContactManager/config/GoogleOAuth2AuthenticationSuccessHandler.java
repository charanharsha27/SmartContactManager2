package com.scm.SmartContactManager.config;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.scm.SmartContactManager.dao.IUserDao;
import com.scm.SmartContactManager.entities.Providers;
import com.scm.SmartContactManager.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class GoogleOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    @Autowired
    private IUserDao userDao;


    Logger logger = org.slf4j.LoggerFactory.getLogger(GoogleOAuth2AuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

                logger.info("Inside onAuthenticationSuccess() method");
                new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
                

                var oauth2Token = (OAuth2AuthenticationToken) authentication;

                String client = oauth2Token.getAuthorizedClientRegistrationId();
                logger.info(client);

                DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
                logger.info("User name: "+authentication.getName());

                user.getAttributes().forEach( (key,value) ->{
                     logger.info("{} -> {},",key,value);
                });

                if(client.equalsIgnoreCase("google")) {
                    User u = new User();
                    u.setEmail(user.getAttribute("email").toString());
                    u.setName(user.getAttribute("name").toString());
                    u.setEmailVerified(true);
                    u.setAbout("Loginned using Google");
                    u.setProfilePic(user.getAttribute("picture").toString());
                    u.setProvider(Providers.GOOGLE);
                    u.setProviderUserId(user.getName());
                    u.setUserId(UUID.randomUUID().toString());

                    User saveUser = userDao.findByEmail(u.getEmail()).orElse(null);

                    if(saveUser == null) {
                        logger.info("Saving user to database");
                        userDao.save(u);
                        logger.info("User saved successfully");
                    }
                }
                else if(client.equalsIgnoreCase("github")){
                    User u = new User();
                    u.setEmail(user.getAttribute("login").toString()+"@gmail.com");
                    u.setProfilePic(user.getAttribute("avatar_url").toString());
                    u.setUserId(UUID.randomUUID().toString());
                    u.setProviderUserId(user.getName());
                    u.setEmailVerified(true);
                    u.setAbout("Loginned using Github");
                    u.setProvider(Providers.GITHUB);
                    u.setName(user.getAttribute("name").toString());

                    User saveUser = userDao.findByEmail(u.getEmail()).orElse(null);

                    if(saveUser == null) {
                        logger.info("Saving user to database");
                        userDao.save(u);
                        logger.info("User saved successfully");
                    }

                }

            }

}
