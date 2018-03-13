package com.kids.crm.service;

import com.kids.crm.model.ExamType;
import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.User;
import com.kids.crm.pojo.LastAttendedResult;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

@Component
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private StudentAnswerRepository studentAnswerRepository;
    private UserSession userSession;

    @Autowired
    public UserService(UserRepository userRepository, StudentAnswerRepository studentAnswerRepository, UserSession userSession) {
        this.userRepository = userRepository;
        this.studentAnswerRepository = studentAnswerRepository;
        this.userSession = userSession;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " username Not Found"));
        return user;
    }

    public List<LastAttendedResult> lastAttendedResults(User user, Date from, Date to){
        List<StudentAnswer> studentAnswersWeekly = studentAnswerRepository.findByUserAndAttendedOnBetweenAndExamType(user, from, to, ExamType.DAILY_EXAM);//oneWeek

        Map<String, LastAttendedResult> resultsMap = new HashMap<>();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        studentAnswersWeekly
                .stream()
                .filter(answer -> Objects.equals(answer.getSubject().getId(), userSession.getCurrentSubject().getId()))
                .forEach(answer -> {
                            if (resultsMap.containsKey(df.format(answer.getAttendedOn()))) {
                                LastAttendedResult lastAttendedResult = resultsMap.get(df.format(answer.getAttendedOn()));
                                if (answer.isGotCorrect()) {
                                    lastAttendedResult.setCorrect(lastAttendedResult.getCorrect() + 1);
                                } else {
                                    lastAttendedResult.setWrong(lastAttendedResult.getWrong() + 1);
                                }
                            } else {
                                resultsMap.putIfAbsent(df.format(answer.getAttendedOn()), new LastAttendedResult(answer.isGotCorrect() ? 1 : 0,
                                        answer.isGotCorrect() ? 0 : 1, answer.getAttendedOn()));
                            }
                        }
                );

        List<LastAttendedResult> lastAttendedResults = new ArrayList<>(resultsMap.values());
        lastAttendedResults.sort(new Comparator<LastAttendedResult>() {
            @Override
            public int compare(LastAttendedResult o1, LastAttendedResult o2) {
                if (o1 == null || o2 == null) {
                    return 0;
                }
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        return lastAttendedResults;
    }

    public List<LastAttendedResult> lastAttendedResultsWeekly(User user){
        Date weekStart = DateUtils.toDate(LocalDate.now().minusDays(7));
        Date weekEnd = DateUtils.toDate(LocalDate.now());
        return lastAttendedResults(user, weekStart, weekEnd);
    }


}
