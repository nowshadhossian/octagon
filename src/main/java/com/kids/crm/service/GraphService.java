package com.kids.crm.service;

import com.kids.crm.json.graph.GraphStudentResult;
import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.User;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.utils.DateUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GraphService {
    @Autowired
    private StudentAnswerRepository studentAnswerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    GraphService graphService;
    @Autowired
    QuestionRepository questionRepository;

    public List<GraphStudentResult> getGraphStudentResultList(User user) {
        List<GraphStudentResult> graphStudentResults = new ArrayList<>();
        Date to =  DateUtils.toDate(LocalDate.now().plusDays(1));
        Date from = DateUtils.oneMonthBack(to);

        List<StudentAnswer> studentAnswerList = studentAnswerRepository.findByUserAndAttendedOnBetween(user, from, to);
        for (LocalDate index = DateUtils.toLocalDate(from); !index.isEqual(DateUtils.toLocalDate(to)); index = index.plusDays(1)) {
            LocalDate finalIndex = index;
            graphStudentResults.add(GraphStudentResult.builder()
                    .date(index)
                    .correct(studentAnswerList.stream()
                            .filter(studentAnswer -> finalIndex.isEqual(DateUtils.toLocalDate(studentAnswer.getAttendedOn())))
                            .filter(StudentAnswer::isGotCorrect)
                            .count())
                    .wrong(studentAnswerList.stream()
                            .filter(studentAnswer -> finalIndex.isEqual(DateUtils.toLocalDate(studentAnswer.getAttendedOn())))
                            .filter(studentAnswer -> !studentAnswer.isGotCorrect())
                            .count())
                    .build());
        }


        return graphStudentResults.stream()
                .sorted(Comparator.comparing(GraphStudentResult::getDate))
                .collect(Collectors.toList());

    }

    public HashMap<Date, HashMap<String, Integer>> graphStudentMarksMap(User user){
        Date to = new Date();
        Date from = DateUtils.daysBack15(to);
        List<StudentAnswer> studentAnswerList = studentAnswerRepository.findByUserAndAttendedOnBetween(user, from, to);
        studentAnswerList.sort((StudentAnswer o1, StudentAnswer o2) ->
                new CompareToBuilder()
                        .append(o1.getAttendedOn(), o2.getAttendedOn())
                        .append(o1.getQuestion().getTopic().getName(), o2.getQuestion().getTopic().getName()).toComparison());
        Date globaldate = from;

        String globalTopic = studentAnswerList.get(0).getQuestion().getTopic().getName();
        int globalMarks = 0;
        int incorrectTopic = 0;

        HashMap<String, Integer> innerMap = new HashMap<String, Integer>();
        HashMap<Date, HashMap<String, Integer>> outerMap = new HashMap<Date, HashMap<String, Integer>>();

        for (int i = 0; i < studentAnswerList.size(); i++) {
            if (globaldate.compareTo(studentAnswerList.get(i).getAttendedOn()) == 0){
                if (globalTopic.equals(studentAnswerList.get(i).getQuestion().getTopic().getName())){
                    if (studentAnswerList.get(i).isGotCorrect())
                        globalMarks += 1;
                    else
                        incorrectTopic += 1;
                    if (i == studentAnswerList.size()-1){
                        innerMap.put(studentAnswerList.get(i).getQuestion().getTopic().getName(),(globalMarks*100)/(globalMarks+incorrectTopic));
                        outerMap.put(globaldate, innerMap);
                    }
                }
                else{

                    innerMap.put(studentAnswerList.get(i-1).getQuestion().getTopic().getName(),(globalMarks*100)/(globalMarks+incorrectTopic));
                    if (studentAnswerList.get(i).isGotCorrect()){
                        globalMarks = 1;
                        incorrectTopic = 0;
                    }
                    else{
                        incorrectTopic = 1;
                        globalMarks = 0;
                    }
                    globalTopic = studentAnswerList.get(i).getQuestion().getTopic().getName();
                    if (i == studentAnswerList.size()-1){
                        innerMap.put(studentAnswerList.get(i).getQuestion().getTopic().getName(),(globalMarks*100)/(globalMarks+incorrectTopic));
                        outerMap.put(globaldate, innerMap);
                    }
                }
            }
            else {
                if (i == 0){
                    if (studentAnswerList.get(i).isGotCorrect()){
                        globalMarks = 1;
                        incorrectTopic = 0;
                    }
                    else{
                        incorrectTopic = 1;
                        globalMarks = 0;
                    }
                    globaldate = studentAnswerList.get(i).getAttendedOn();
                    globalTopic = studentAnswerList.get(i).getQuestion().getTopic().getName();
                }
                else {
                    innerMap.put(studentAnswerList.get(i-1).getQuestion().getTopic().getName(),(globalMarks*100)/(globalMarks+incorrectTopic));
                    outerMap.put(globaldate, innerMap);
                    innerMap = new HashMap<String, Integer>();
                    if (studentAnswerList.get(i).isGotCorrect()){
                        globalMarks = 1;
                        incorrectTopic = 0;
                    }
                    else{
                        incorrectTopic = 1;
                        globalMarks = 0;
                    }
                    globaldate = studentAnswerList.get(i).getAttendedOn();
                    globalTopic = studentAnswerList.get(i).getQuestion().getTopic().getName();
                    if (i == studentAnswerList.size()-1){
                        innerMap.put(studentAnswerList.get(i).getQuestion().getTopic().getName(),(globalMarks*100)/(globalMarks+incorrectTopic));
                        outerMap.put(globaldate, innerMap);
                    }
                }

            }
        }

        return outerMap;
    }
}
