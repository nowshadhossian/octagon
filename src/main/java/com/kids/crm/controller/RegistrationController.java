package com.kids.crm.controller;

import com.kids.crm.model.Signup;
import com.kids.crm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {


    private final StudentService studentService;

    @Autowired
    public RegistrationController(StudentService studentService) {
        this.studentService = studentService;
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    private String register(Model model){
        model.addAttribute("studentList", studentService.findAllStudent());
        model.addAttribute("msg", "Introduction of Freemarker in Project");
        return "register";
    }

    @RequestMapping(value = "/register-form", method = RequestMethod.POST)
    private String registerProcess(Model model, @ModelAttribute("signup") Signup signup, BindingResult bindingResult){
        model.addAttribute("studentList", studentService.findAllStudent());
        model.addAttribute("msg", "Introduction of Freemarker in Project");
        return "register";
    }

}
