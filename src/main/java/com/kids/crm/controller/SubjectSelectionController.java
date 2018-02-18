package com.kids.crm.controller;

import com.kids.crm.model.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SubjectSelectionController {
    private static final String BASE_ROUTE = "/subject";

    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
    private String test(Authentication authentication, ModelMap modelMap){
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        modelMap.addAttribute("name", authUser.getName());
        return "subject-page";
    }
}
