package com.kids.crm.controller.superadmin;

import com.kids.crm.model.Session;
import com.kids.crm.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/superadmin/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;
    @GetMapping(value = {"","/"})
    public String sessionPage(ModelMap modelMap) {
        List<Session> sessions = sessionService.getAllSessions();
        modelMap.addAttribute("sessions",sessions);
        return "super/session-page";
    }
    @GetMapping("/create")
    public String createNewSessionForm(ModelMap modelMap){
        modelMap.addAttribute("session", new Session());
        return "super/session-create";
    }
    @GetMapping("/{sessionId}/edit")
    public String editSessionForm(ModelMap modelMap,@PathVariable(value="sessionId",required = false) long sessionId){
        if (!Long.toString(sessionId).matches("\\d+")){
            throw new IllegalArgumentException();
        }
        if (sessionId>0){
            Session session = sessionService.findSessionById(sessionId);
            modelMap.addAttribute("session",session);
        } else {
            modelMap.addAttribute("session", new Session());
        }
        return "super/session-create";
    }
    @PostMapping("/create")
    public String createNewSession(@ModelAttribute Session form){
        Session session = Session.builder().id(form.getId()).build();
        session.setName(form.getName());
        session.setYear(form.getYear());
        session.setExamDate(form.getExamDate());
        sessionService.saveSession(session);
        return "redirect:/superadmin/session";
    }
}
