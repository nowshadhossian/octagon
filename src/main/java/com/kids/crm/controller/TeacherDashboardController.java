package com.kids.crm.controller;

import com.kids.crm.controller.webcomponent.LeaderboardComponent;
import com.kids.crm.model.Batch;
import com.kids.crm.model.Student;
import com.kids.crm.model.Teacher;
import com.kids.crm.model.User;
import com.kids.crm.pojo.LastAttendedResult;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.TeacherRepository;
import com.kids.crm.service.StudentService;
import com.kids.crm.service.UserService;
import com.kids.crm.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class TeacherDashboardController {
    private static final String BASE_ROUTE = "/teacher/dashboard";

    @Autowired
    StudentAnswerRepository studentAnswerRepository;
    @Autowired
    UserService userService;

    @Autowired
    UserSession userSession;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    LeaderboardComponent leaderboardComponent;

    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
    private String teacherDashboard(Authentication authentication, ModelMap modelMap) {
        User loggedIn = userSession.getLoggedInUser();
        modelMap.addAttribute("name", loggedIn.getName());
        Teacher teacher = teacherRepository.findById(loggedIn.getId()).get();

        modelMap.addAttribute("leaderboardPage", leaderboardComponent.draw(userSession.getCurrentBatch(), modelMap));

        return "teacher/dashboard";
    }
}
