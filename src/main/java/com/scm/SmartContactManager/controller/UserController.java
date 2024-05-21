package com.scm.SmartContactManager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.SmartContactManager.helper.GetLoggedInUserName;
@Controller
@RequestMapping("/user")
public class UserController {

    //user dashboard
    // @GetMapping("/dashboard")
    // public String userDashboard(Authentication authentication) {
    //     System.out.println("In user dashboard handler");
    //     GetLoggedInUserName.getLoggedInUserEmail(authentication);
    //     return "user/dashboard";
    // }

    @GetMapping("/dashboard")
    public String userDashboard(Authentication authentication) {
        System.out.println("In user dashboard handler");
        String username = GetLoggedInUserName.getLoggedInUserEmail(authentication);
        System.out.println("Username: "+username);
        return "user/dashboard";
    }

}
