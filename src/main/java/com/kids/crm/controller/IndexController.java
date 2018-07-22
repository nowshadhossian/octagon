package com.kids.crm.controller;

import com.kids.crm.config.AfterLoginSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    AfterLoginSuccess afterLoginSuccess;

    @GetMapping("/")
    public String index(Authentication authentication){
        return "redirect:" + afterLoginSuccess.determineTargetUrl(authentication);
    }
}
