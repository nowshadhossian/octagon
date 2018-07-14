package com.kids.crm.controller.superadmin;

import com.kids.crm.model.Session;
import com.kids.crm.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/superadmin/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;
    @GetMapping(value = {"","/"})
    public String sessionPage(@ModelAttribute("model")ModelMap modelMap) {
        List<Session> allSession = sessionService.getAllSessions();
        modelMap.addAttribute("allSession",allSession);
        return "super/session-page";
    }
    @GetMapping("/create")
    public String createNewSessionForm(@ModelAttribute("model")ModelMap modelMap){
        return "super/session-create";
    }
    @PostMapping("/create")
    public String createNewSession(@ModelAttribute Session session){
        sessionService.saveSession(session);
        return "redirect:/superadmin/session";
    }
}
