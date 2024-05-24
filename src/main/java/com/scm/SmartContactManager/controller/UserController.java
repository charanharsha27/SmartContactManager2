package com.scm.SmartContactManager.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.forms.ContactForm;
import com.scm.SmartContactManager.helper.GetLoggedInUserName;
import com.scm.SmartContactManager.service.IUserService;
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    private Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);


    @GetMapping("/dashboard")
    public String userDashboard(Authentication authentication) {
        System.out.println("In user dashboard handler");
        
        return "user/dashboard";
    }

    @GetMapping("contacts/add")
    public String addContact(Model model){
        model.addAttribute("contactForm",new ContactForm());
        return "user/add_contact";
    }

}
