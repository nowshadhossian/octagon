package com.kids.crm.controller.assistant;

import com.kids.crm.model.User;
import com.kids.crm.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/assistant/dashboard")
public class AssistantDashboardController {
    private final UserSession userSession;

    @Autowired
    public AssistantDashboardController(UserSession userSession) {
        this.userSession = userSession;
    }

    @GetMapping
    public String dashboardIndex(ModelMap modelMap){
        User loggedIn = userSession.getLoggedInUser();
        modelMap.addAttribute("name", loggedIn.getName());
        return "assistant/dashboard";
    }
}
