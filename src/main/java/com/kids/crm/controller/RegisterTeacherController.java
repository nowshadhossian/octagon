package com.kids.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterTeacherController {

    private final String BASE_ROUTE = "register/teacher";

    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
    public String register(Model model){
      /*  model.addAttribute("studentList", studentService.findAllStudent());
        model.addAttribute("msg", "Introduction of Freemarker in Project");
        model.addAttribute("signup", new Signup());*/
        return "register";
    }
}
