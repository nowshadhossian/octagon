package com.kids.crm.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log
public class LoginController {
    @RequestMapping(value ="/login")
    public String login(){
        log.info("Login Page displayed");
        return "login-page";
    }
}
