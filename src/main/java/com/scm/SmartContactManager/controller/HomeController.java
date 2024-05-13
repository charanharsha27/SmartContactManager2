package com.scm.SmartContactManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.forms.UserForm;
import com.scm.SmartContactManager.service.IUserServiceImpl;

@Controller
public class HomeController {

    @Autowired
    private IUserServiceImpl userService;

    @GetMapping("/index")
    public String home(Model model) {
        System.out.println("In home page handler");
        // model.addAttribute("name", "Hello Charan");
        return "index";
    }

    //about page
    @GetMapping("/about")
    public String aboutPage(Model model) {
        System.out.println("In about page handler");
        model.addAttribute("isLogin",false);
        model.addAttribute("name", "Hello Charan");
        return "about";
    }
    
    //about page
    @GetMapping("/services")
    public String servicesPage(Model model) {
        System.out.println("In services page handler");
        model.addAttribute("name", "Hello Charan");
        return "services";
    }

    //contact page
    @GetMapping("/contact")
    public String contactPage(Model model) {
        System.out.println("In contact page handler");
        model.addAttribute("name", "Hello Charan");
        return "contact";
    }

    //login page
    @GetMapping("/login")
    public String loginPage(Model model) {
        System.out.println("In login page handler");
        model.addAttribute("name", "Hello Charan");
        return "login";
    }

    //dignup page
    @GetMapping("/signup")
    public String signUp(Model model) {
        UserForm userForm = new UserForm();
        userForm.setName("Charan");
        userForm.setEmail("charan@gmail.com");
        model.addAttribute("user", userForm);
        return "signup";
    }

    @PostMapping("/process-register")
    public String processRegister(@ModelAttribute UserForm userForm){
        System.out.println(userForm);

        User u = User.builder()
        .name(userForm.getName())
        .email(userForm.getEmail())
        .password(userForm.getPassword())
        .about(userForm.getAbout())
        .build();

        User user = userService.saveUser(u);
        System.out.println("User saved :"+user);
        return "redirect:signup";
    }
}
