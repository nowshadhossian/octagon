package com.kids.crm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SubjectSelectionController {
    private static final String BASE_ROUTE = "/subject";

    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
    private String test(Authentication authentication, ModelMap modelMap){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        modelMap.addAttribute("name", userDetails.getName());
        return "subject-page";
    }
}
