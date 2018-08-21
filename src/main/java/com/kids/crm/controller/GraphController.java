package com.kids.crm.controller;

import com.kids.crm.json.graph.GraphStudentResult;
import com.kids.crm.model.User;
import com.kids.crm.service.GraphService;
import com.kids.crm.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(StudentDashboardController.BASE_ROUTE + "/graph")
public class GraphController {
    private final GraphService graphService;
    private final UserSession userSession;

    @Autowired
    public GraphController(GraphService graphService, UserSession userSession) {
        this.graphService = graphService;
        this.userSession = userSession;
    }

    @GetMapping
    public String dashboardIndex(ModelMap modelMap){
        modelMap.addAttribute("authenticatedUser", userSession.getLoggedInUser());
        return "/student/graph";
    }

    @GetMapping("/studentDailyRightWrong/data")
    @ResponseBody
    public List<GraphStudentResult> correctWrong(){
        User loggedIn = userSession.getLoggedInUser();
        return graphService.getGraphStudentResultList(loggedIn);
    }

//    @GetMapping("/subtopic/daily/progress/data")
//    @ResponseBody
//    public HashMap<Date, HashMap<String, Integer>> table(){
//        return graphService.graphStudentMarksMap(userSession.getLoggedInUser());
//    }

    @GetMapping("/radar/data")
    @ResponseBody
    public HashMap<String, Integer> spiderController(){
         return graphService.spiderServiceMap(userSession.getLoggedInUser());
    }

    @GetMapping("/subtopic/daily/progress/data")
    @ResponseBody
    public HashMap<Date, HashMap<String, Integer>> table(){
        return graphService.htmlTableMap(userSession.getLoggedInUser());
    }

    @GetMapping("/topicSubTopicMarks/data")
    @ResponseBody
    public HashMap<String, Integer> topicSubTopicMarks(){
        return graphService.TopicSubtopicMarksMap(userSession.getLoggedInUser());
    }

    @GetMapping("/topicWiseMarks/data")
    @ResponseBody
    public HashMap<String, Integer> topicWiseMarks(){
        return graphService.TopicWiseMarksMap(userSession.getLoggedInUser());
    }


}
