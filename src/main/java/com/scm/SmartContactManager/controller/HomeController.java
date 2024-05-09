package com.scm.SmartContactManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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
}
