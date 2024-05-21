package com.scm.SmartContactManager.helper;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class GetLoggedInUserName {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(GetLoggedInUserName.class);

    public static String getLoggedInUserEmail(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken) {
            var oauth2Token = (OAuth2AuthenticationToken) authentication;
            String client = oauth2Token.getAuthorizedClientRegistrationId(); // retreiving the client name
            var oauth2User = (OAuth2User)authentication.getPrincipal(); // getting the current user
            logger.info("Clinet : "+client);
            if(client.equalsIgnoreCase("google")){
                return oauth2User.getAttribute("email").toString(); // returning the current user email logged via google
            }
            else if(client.equalsIgnoreCase("github")){
                return oauth2User.getAttribute("email")!=null?oauth2User.getAttribute("email").toString()
                    :oauth2User.getAttribute("login").toString()+"@gmail.com";
            }
            return null;
        }
        else{
            return authentication.getName().toString();
        }

        
    }
}
