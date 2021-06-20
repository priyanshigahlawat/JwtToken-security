package com.example.SMSdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PortalController {

    @GetMapping("/login")
    public String display(){
        return "login";
    }

    @GetMapping("/dashboard")
    public String display2(){
        return "dashboard";
    }
}
