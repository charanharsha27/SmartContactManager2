package com.scm.SmartContactManager.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.SmartContactManager.entities.Contact;
import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.forms.ContactForm;
import com.scm.SmartContactManager.helper.GetLoggedInUserName;
import com.scm.SmartContactManager.helper.MessageHelper;
import com.scm.SmartContactManager.service.IContactService;
import com.scm.SmartContactManager.service.IUserService;
import com.scm.SmartContactManager.service.ImageService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private IContactService contactService;

    private Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);


    @GetMapping("/dashboard")
    public String userDashboard(Authentication authentication) {
        System.out.println("In user dashboard handler");
        
        return "user/dashboard";
    }

    

}
