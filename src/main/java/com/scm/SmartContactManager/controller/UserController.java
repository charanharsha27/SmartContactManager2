package com.scm.SmartContactManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/user")
public class UserController {

    //user dashboard
    @GetMapping("/dashboard")
    public String userDashboard() {
        System.out.println("In user dashboard handler");
        return "user/dashboard";
    }
}
