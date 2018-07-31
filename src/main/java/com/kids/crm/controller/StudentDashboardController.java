package com.kids.crm.controller;

import com.kids.crm.controller.webcomponent.LeaderboardComponent;

import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.Subject;
import com.kids.crm.model.User;
import com.kids.crm.repository.StudentAnswerRepository;

import com.kids.crm.service.StudentService;

import com.kids.crm.service.SubjectService;
import com.kids.crm.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentDashboardController {
    public static final String BASE_ROUTE = "/student/dashboard";

    @Autowired
    StudentAnswerRepository studentAnswerRepository;

    @Autowired
    UserSession userSession;

    @Autowired
    StudentService studentService;

    @Autowired
    LeaderboardComponent leaderboardComponent;

    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
    private String getStudentDashboard(Authentication authentication, ModelMap modelMap) {
        User loggedIn = userSession.getLoggedInUser();
        modelMap.addAttribute("name", loggedIn.getName());

        modelMap.addAttribute("lastWeeklyResults", studentService.lastAttendedResultsWeekly(loggedIn, userSession.getCurrentBatch()));

        modelMap.addAttribute("leaderboardTodayPageToInclude", leaderboardComponent.drawForToday(userSession.getCurrentBatch(), modelMap));
        modelMap.addAttribute("loggedIn", userSession.getLoggedInUser());

        return "subject-page";
    }

    @GetMapping(BASE_ROUTE+"/question-checklist")
    public String showQuestionCheckList(ModelMap modelMap, @RequestParam (value ="_subject",required = false) String subjectParam){
        User loggedInUser = userSession.getLoggedInUser();
        List<StudentAnswer> studentAnswers;
        if (subjectParam==null || subjectParam.equalsIgnoreCase("all")){
            studentAnswers = studentService.getStudentAnswerByUserIdAndBatchId(loggedInUser.getId(),userSession.getCurrentBatch().getId());
        } else{
            studentAnswers = studentService.getStudentAnswerByUserIdAndBatchIdAndSubject(loggedInUser.getId(),userSession.getCurrentBatch().getId(),subjectParam);
        }
        if (subjectParam!=null){
            modelMap.addAttribute("selectedFilter",subjectParam);
        }
        modelMap.addAttribute("studentAnswers",studentAnswers);

        List<Subject> subjects = subjectService.getSubjects();
        modelMap.addAttribute("subjects",subjects);

        return "question-checklist";
    }
}
