package com.kids.crm.controller.webcomponent;

import com.kids.crm.model.Batch;
import com.kids.crm.model.Student;
import com.kids.crm.pojo.LastAttendedResult;
import com.kids.crm.service.StudentService;
import com.kids.crm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.time.LocalDate;
import java.util.*;

@Component
public class LeaderboardComponent {
    private StudentService studentService;

    @Autowired
    public LeaderboardComponent(StudentService studentService) {
        this.studentService = studentService;
    }

    public String drawForToday(Batch batch, ModelMap modelMap){
        Date from = DateUtils.toDate(LocalDate.now().minusDays(0)); //2
        Date to = DateUtils.toDate(LocalDate.now().minusDays(-1));
        return draw(batch, modelMap, from, to);
    }

    public String drawForYesterday(Batch batch, ModelMap modelMap){
        Date from = DateUtils.toDate(LocalDate.now().minusDays(1)); //2
        Date to = DateUtils.toDate(LocalDate.now().minusDays(0));
        return draw(batch, modelMap, from, to);
    }

    private String draw(Batch batch, ModelMap modelMap, Date from, Date to){
        List<Student> students = batch.getStudents();
        List<LastAttendedResult> attendedResult = new ArrayList<>();
        for(Student student : students){
            studentService.singleDayAttendedResults(student, batch, from, to)
                    .ifPresent(attendedResult::add);
        }


        attendedResult.sort(new Comparator<LastAttendedResult>() {
            @Override
            public int compare(LastAttendedResult o1, LastAttendedResult o2) {
                if(o1 == null || o2 == null){
                    return 0;
                }
                return o2.getCorrect() > o1.getCorrect() ? 1 : -1;
            }
        });

        modelMap.addAttribute("leaderboardYesterdayResults", attendedResult);

        return "/student/stat/leaderboard.ftl";
    }

}
