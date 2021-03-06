package com.kids.crm.controller;

import com.kids.crm.controller.webcomponent.LeaderboardComponent;
import com.kids.crm.model.User;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.service.StudentService;
import com.kids.crm.service.UserService;
import com.kids.crm.service.UserSession;
import com.kids.crm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.Date;

@Controller
public class StudentStatsController {
    private static final String BASE_ROUTE = "/student/stats";
    private static final String LAST_DAILY_EXAM_RESULTS_ROUTE = BASE_ROUTE + "/daily-results";
    private static final String LEADERBOARD_YESTERDAY_RESULTS_ROUTE = BASE_ROUTE + "/leaderboard-yesterday-results";

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

    @RequestMapping(value = LAST_DAILY_EXAM_RESULTS_ROUTE, method = RequestMethod.GET)
    private String getLastDailyExamResultsRoute(Authentication authentication, ModelMap modelMap) {
        User loggedIn = userSession.getLoggedInUser();

        Date from = DateUtils.toDate(LocalDate.now().minusYears(2));
        Date to = DateUtils.toDate(LocalDate.now().minusDays(-1));
        modelMap.addAttribute("lastWeeklyResults", studentService.lastAttendedResults(loggedIn, from, to, userSession.getCurrentBatch()));

        return "/student/stat/last-daily-results";
    }

    @RequestMapping(value = LEADERBOARD_YESTERDAY_RESULTS_ROUTE, method = RequestMethod.GET)
    private String leaderboardPageYesterday(Authentication authentication, ModelMap modelMap) {
        modelMap.addAttribute("leaderboardYesterdayForInclude", leaderboardComponent.drawForYesterday(userSession.getCurrentBatch(), modelMap));
        modelMap.addAttribute("loggedIn", userSession.getLoggedInUser());
        return "/student/stat/leaderboard-yesterday";
    }
}
