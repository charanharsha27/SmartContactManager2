package com.scm.SmartContactManager.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.helper.GetLoggedInUserName;
import com.scm.SmartContactManager.service.IUserService;

@ControllerAdvice // this annotation is used to handle the exceptions.
public class AuthResponseHandler {
    
    @Autowired
    private IUserService userService;

    Logger logger = org.slf4j.LoggerFactory.getLogger(AuthResponseHandler.class);

    @ModelAttribute // get the data object even before the request mapping is called.
    public void AddUserDetailsToResponse(Authentication authentication,Model model){

        if(authentication == null){
            return; // when the user is not loggedin yet.
        }

        logger.info("Retreiving user detials from db");
        String username = GetLoggedInUserName.getLoggedInUserEmail(authentication);
        User user = userService.getUserByEmail(username);
        logger.info("User email from db : "+user);
        System.out.println("Username: "+username);
        
        if(user!=null){
            model.addAttribute("user", user);
        }
        else{
            model.addAttribute("user",null);
        }
    }

}
