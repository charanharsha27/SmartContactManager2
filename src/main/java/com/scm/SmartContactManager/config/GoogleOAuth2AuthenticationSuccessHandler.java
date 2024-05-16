package com.scm.SmartContactManager.config;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
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
                
                //collecting the user details
                DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
                logger.info("User name: "+authentication.getName());

                user.getAttributes().forEach( (key,value) ->{
                     logger.info("{} -> {},",key,value);
                });

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

}
