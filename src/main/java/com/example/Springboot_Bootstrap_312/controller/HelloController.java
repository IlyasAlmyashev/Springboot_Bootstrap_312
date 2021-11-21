package com.example.Springboot_Bootstrap_312.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping(value = "/")
    public String redirect(ModelMap model) {
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "loginpage";
    }

}