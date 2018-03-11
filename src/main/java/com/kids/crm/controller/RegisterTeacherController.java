package com.kids.crm.controller;

import com.kids.crm.model.Role;
import com.kids.crm.model.Signup;
import com.kids.crm.model.Teacher;
import com.kids.crm.model.User;
import com.kids.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterTeacherController {

    private final String BASE_ROUTE = "register/teacher";

    @Autowired private UserRepository userRepository;



    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("signup", new Signup());
        return "teacher/register";
    }

    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.POST)
    public String registerTeacher(Model model, Signup signup){
        User teacher = Teacher.builder()
                .address(signup.getAddress())
                .degree(signup.getDegree())
                .phone(signup.getPhoneNo())
                .build();
        teacher.setFirstName(signup.getFirstName());
        teacher.setPassword(signup.getPassword());
        teacher.setRole(Role.TEACHER);
        teacher.setEmail(signup.getEmail());
        teacher.setLastName(signup.getLastName());

        userRepository.save(teacher);


        return "redirect:/login";
    }
}
