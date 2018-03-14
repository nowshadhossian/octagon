package com.kids.crm.controller.webcomponent;

import com.kids.crm.model.Batch;
import com.kids.crm.model.Student;
import com.kids.crm.pojo.LastAttendedResult;
import com.kids.crm.service.StudentService;
import com.kids.crm.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class LeaderboardComponent {
    private StudentService studentService;

    @Autowired
    public LeaderboardComponent(StudentService studentService) {
        this.studentService = studentService;
    }

    public String draw(Batch batch, ModelMap modelMap){
        List<Student> students = batch.getStudents();
        List<LastAttendedResult> attendedResult = new ArrayList<>();
        for(Student student : students){
            studentService.previousDayAttendedResults(student, batch)
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

        modelMap.addAttribute("leaderboardAttendedResults", attendedResult);

        return "/student/stat/leaderboard.ftl";
    }

}
