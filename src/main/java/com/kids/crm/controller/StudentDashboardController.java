package com.kids.crm.controller;

import com.kids.crm.controller.webcomponent.LeaderboardComponent;

import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.Topic;
import com.kids.crm.model.User;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.service.StudentResultService;
import com.kids.crm.service.StudentService;
import com.kids.crm.service.TopicService;
import com.kids.crm.service.UserSession;
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
    public String showQuestionCheckList(ModelMap modelMap, @RequestParam (value ="_topic",required = false) String topicParam){
        User loggedInUser = userSession.getLoggedInUser();
        List<StudentAnswer> studentAnswers;
        if (topicParam==null || topicParam.equalsIgnoreCase("all")){
            studentAnswers = studentService.getStudentAnswerByUserIdAndBatchId(loggedInUser.getId(),userSession.getCurrentBatch().getId());
        } else{
            studentAnswers = studentService.getStudentAnswerByUserIdAndBatchIdAndTopicId(loggedInUser.getId(),userSession.getCurrentBatch().getId(),Long.parseLong(topicParam));
        }
        modelMap.addAttribute("selectedFilter",topicParam);

        modelMap.addAttribute("studentAnswers",studentAnswers);

        List<Topic> topics = topicService.getAllTopic();
        modelMap.addAttribute("topics",topics);

        return "question-checklist";
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

}
