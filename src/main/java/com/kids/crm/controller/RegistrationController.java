package com.kids.crm.controller;

import com.kids.crm.model.Batch;
import com.kids.crm.model.Session;
import com.kids.crm.model.Signup;
import com.kids.crm.model.Subject;
import com.kids.crm.service.BatchService;
import com.kids.crm.service.StudentService;
import com.kids.crm.validator.SignupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {


    private final StudentService studentService;
    private final SignupValidator validator;
    private final BatchService batchService;

    @Autowired
    public RegistrationController(StudentService studentService, SignupValidator validator, BatchService batchService) {
        this.studentService = studentService;
        this.validator = validator;
        this.batchService = batchService;
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("signup", new Signup());

        List<Batch> upcomingBatches = batchService.getUpcomingBatches();
        List<Session> upcomingSessions = upcomingBatches.stream()
                .map(Batch::getSession)
                .collect(Collectors.toList());
        List<Subject> upcomingSubjects = upcomingBatches.stream()
                .map(batch -> batch.getSubject())
                .collect(Collectors.toList());
        model.addAttribute("upcomingSessions", upcomingSessions);
        model.addAttribute("upcomingSubjects", upcomingSubjects);

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, params = "register")
    public String registerProcess(Model model, @ModelAttribute Signup signup, BindingResult bindingResult){
        validator.validate(signup, bindingResult);
        if(bindingResult.hasErrors()){
            return "register";
        }
        return "dashboard";
    }
}
