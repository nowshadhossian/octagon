package com.kids.crm.controller;

import com.kids.crm.controller.webcomponent.LeaderboardComponent;
import com.kids.crm.model.ExamType;
import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.User;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.service.StudentService;
import com.kids.crm.service.UserService;
import com.kids.crm.service.UserSession;
import com.kids.crm.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
public class SubjectSelectionController {
    private static final String BASE_ROUTE = "/subject";

    @Autowired
    StudentAnswerRepository studentAnswerRepository;
    @Autowired
    UserService userService;

    @Autowired
    UserSession userSession;

    @Autowired
    StudentService studentService;

    @Autowired
    LeaderboardComponent leaderboardComponent;

    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
    private String test(Authentication authentication, ModelMap modelMap) {
        User loggedIn = userSession.getLoggedInUser();
        modelMap.addAttribute("name", loggedIn.getName());

        modelMap.addAttribute("lastWeeklyResults", studentService.lastAttendedResultsWeekly(loggedIn, userSession.getCurrentBatch()));

        modelMap.addAttribute("leaderboardYesterdayPage", leaderboardComponent.draw(userSession.getCurrentBatch(), modelMap));

        return "subject-page";
    }


}
