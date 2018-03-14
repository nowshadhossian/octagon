package com.kids.crm.service;

import com.kids.crm.model.*;
import com.kids.crm.pojo.LastAttendedResult;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.StudentRepository;
import com.kids.crm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

@Component
public class StudentService {
    private StudentRepository repository;
    private StudentAnswerRepository studentAnswerRepository;

    @Autowired
    public StudentService(StudentRepository repository, StudentAnswerRepository studentAnswerRepository) {
        this.repository = repository;
        this.studentAnswerRepository = studentAnswerRepository;
    }

    public void saveStudent(String name, int age) {
        Student student = new Student();
      /*  student.setAge(age);
        student.setName(name);*/

        repository.save(student);
    }

   /* public Student findByName(String name){
        return repository.findByName(name).stream().findFirst().orElse(new Student());
    }*/

    public List<Student> findAllStudent() {
        return repository.findAll();
    }

    public List<LastAttendedResult> lastAttendedResults(User user, Date from, Date to, Batch batch){
        List<StudentAnswer> studentAnswersWeekly = studentAnswerRepository.findByUserAndAttendedOnBetweenAndExamType(user, from, to, ExamType.DAILY_EXAM);

        Map<String, LastAttendedResult> resultsMap = new HashMap<>();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        studentAnswersWeekly
                .stream()
                .filter(answer -> Objects.equals(answer.getSubject().getId(), batch.getId()))
                .forEach(answer -> {
                            if (resultsMap.containsKey(df.format(answer.getAttendedOn()))) {
                                LastAttendedResult lastAttendedResult = resultsMap.get(df.format(answer.getAttendedOn()));
                                if (answer.isGotCorrect()) {
                                    lastAttendedResult.incrementCorrect();
                                } else {
                                    lastAttendedResult.incrementWrong();
                                }
                            } else {
                                resultsMap.putIfAbsent(df.format(answer.getAttendedOn()), new LastAttendedResult(answer.isGotCorrect() ? 1 : 0,
                                        answer.isGotCorrect() ? 0 : 1, answer.getAttendedOn(), user));
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

    public List<LastAttendedResult> lastAttendedResultsWeekly(User user, Batch batch){
        Date weekStart = DateUtils.toDate(LocalDate.now().minusDays(7));
        Date weekEnd = DateUtils.toDate(LocalDate.now());
        return lastAttendedResults(user, weekStart, weekEnd, batch);
    }

    public Optional<LastAttendedResult> previousDayAttendedResults(User user, Batch batch){
        Date from = DateUtils.toDate(LocalDate.now().minusDays(20)); //2
        Date to = DateUtils.toDate(LocalDate.now().minusDays(1));
        return lastAttendedResults(user, from, to, batch).stream().findFirst();
    }
}
