package com.kids.crm.controller;

import com.kids.crm.controller.webcomponent.LeaderboardComponent;

import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.Topic;
import com.kids.crm.model.User;
import com.kids.crm.model.mongo.QuestionSolvingTime;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    TopicService topicService;

    @Autowired
    StudentResultService studentResultService;

    @Autowired
    QuestionSolvingTimeService questionSolvingTimeService;

    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
    public String getStudentDashboard(Authentication authentication, ModelMap modelMap) {
        User loggedIn = userSession.getLoggedInUser();
        modelMap.addAttribute("name", loggedIn.getName());

        modelMap.addAttribute("lastWeeklyResults", studentService.lastAttendedResultsWeekly(loggedIn, userSession.getCurrentBatch()));

        modelMap.addAttribute("leaderboardTodayPageToInclude", leaderboardComponent.drawForToday(userSession.getCurrentBatch(), modelMap));
        modelMap.addAttribute("loggedIn", userSession.getLoggedInUser());

        return "subject-page";
    }

    @GetMapping(BASE_ROUTE+"/graphs-student-result")
    public String showStudentResultWeekly(ModelMap modelMap){

        return "student/student-result";
    }
    @GetMapping(BASE_ROUTE+"/student-weekly-result")
    @ResponseBody
    public Map<String,Map<String,Map<String,Integer>>> getStudentWeeklyResult(@RequestParam(required = true, value = "weekOffset") int weekOffset){
        User loggedInUser = userSession.getLoggedInUser();
        Map<String,Map<String,Map<String,Integer>>> studentResults = studentResultService.getWeeklyStudentResultTopic(loggedInUser, weekOffset);
        return studentResults;
    }
    @GetMapping(BASE_ROUTE+"/exam-info")
    public String showExamInfo(ModelMap modelMap){
        if(userSession.getCurrentBatch()!=null){
            modelMap.addAttribute("examDate",userSession.getCurrentBatch().getSession().getExamDate());
            List<QuestionSolvingTime> questionSolvingTimeList = questionSolvingTimeService.getQuestionSolvingTimeByUserId(userSession.getLoggedInUser().getId());
            int totalTime = 0;
            for (QuestionSolvingTime questionSolvingTime: questionSolvingTimeList) {
                totalTime+=questionSolvingTime.getDuration();
            }
            if (questionSolvingTimeList.size()>0){
                int avgTime = totalTime/questionSolvingTimeList.size();
                modelMap.addAttribute("avgTime",avgTime);
            }
        }
        return "student/exam-info";
    }

}
