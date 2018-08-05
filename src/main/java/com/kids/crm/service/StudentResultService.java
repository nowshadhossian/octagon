package com.kids.crm.service;

import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.User;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class StudentResultService {

    private final String CORRECT = "correct";
    private final String WRONG = "wrong";

    @Autowired
    StudentAnswerRepository studentAnswerRepository;
    public Map<String,Map<String,Map<String,Integer>>> getWeeklyStudentResultTopic(User user, int weekOffset){
        Date currentDate = new Date();
        Date to = DateUtils.daysBack(currentDate,1+7*weekOffset);
        Date from = DateUtils.daysBack(currentDate,7+7*weekOffset);
        List<StudentAnswer> studentAnswerList = studentAnswerRepository.findByUserAndAttendedOnBetweenOrderByAttendedOn(user,from,to);
        return getStudentResultsMap(studentAnswerList);
    }

    private Map<String,Map<String,Map<String,Integer>>> getStudentResultsMap(List<StudentAnswer> studentAnswerList) {
        Map<String,Map<String,Map<String,Integer>>> studentResults = new HashMap<>();

        for (StudentAnswer studentAnswer: studentAnswerList ) {
            LocalDate attendedDate = studentAnswer.getAttendedOn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String attendedDateAsString = attendedDate.toString();
            if(studentResults.containsKey(attendedDateAsString)){
                Map<String,Map<String,Integer>> topicResult = studentResults.get(attendedDateAsString);
                if(topicResult.containsKey(studentAnswer.getQuestion().getTopic().getName())){
                    Map<String,Integer> answer = topicResult.get(studentAnswer.getQuestion().getTopic().getName());
                    if (studentAnswer.isGotCorrect()){
                        answer.put(CORRECT,answer.get(CORRECT)+1);
                    } else {
                        answer.put(WRONG,answer.get(WRONG)+1);
                    }
                } else{
                    Map<String , Integer> answer = new HashMap<>();
                    assignAnswerResult(studentAnswer, answer);
                    topicResult.put(studentAnswer.getQuestion().getTopic().getName(),answer);
                }
            } else {
                Map<String , Integer> answer = new HashMap<>();
                assignAnswerResult(studentAnswer, answer);
                Map<String, Map<String,Integer>> topicAnswer = new HashMap<>();
                topicAnswer.put(studentAnswer.getQuestion().getTopic().getName(),answer);
                studentResults.put(attendedDateAsString,topicAnswer);
            }

        }
        return studentResults;
    }

    private void assignAnswerResult(StudentAnswer studentAnswer, Map<String, Integer> answer) {
        if (studentAnswer.isGotCorrect()){
            answer.put(CORRECT,1);
            answer.put(WRONG,0);
        } else {
            answer.put(WRONG,1);
            answer.put(CORRECT,0);
        }
    }
}
