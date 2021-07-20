package com.myservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/loginForm")
@Controller
public class LoginController {

    @GetMapping
    public String login() {
        return "loginForm";
    }

}
