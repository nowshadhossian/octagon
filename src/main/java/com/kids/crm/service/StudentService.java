package com.kids.crm.service;

import com.kids.crm.model.*;
import com.kids.crm.pojo.LastAttendedResult;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.StudentRepository;
import com.kids.crm.repository.SubjectRepository;
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
    private SubjectRepository subjectRepository;

    @Autowired
    public StudentService(StudentRepository repository, StudentAnswerRepository studentAnswerRepository) {
        this.repository = repository;
        this.studentAnswerRepository = studentAnswerRepository;
    }

    public Student save(Student student){
        student.setRole(Role.STUDENT);
        return repository.save(student);
    }

   /* public Student findByName(String name){
        return repository.findByName(name).stream().findFirst().orElse(new Student());
    }*/

   public Student findStudentById(long studentId){
       return repository.findStudentById(studentId);
   }

    public List<Student> findAllStudent() {
        return repository.findAll();
    }

    public List<LastAttendedResult> lastAttendedResults(User user, Date from, Date to, Batch batch){
        List<StudentAnswer> studentAnswersWeekly = studentAnswerRepository.findByUserAndAttendedOnBetweenAndExamType(user, from, to, ExamType.DAILY_EXAM);

        Map<String, LastAttendedResult> resultsMap = new HashMap<>();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        studentAnswersWeekly
                .stream()
                .filter(answer -> Objects.equals(answer.getSubject().getId(), batch.getSubject().getId()))
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
        Date weekEnd = DateUtils.toDate(LocalDate.now().minusDays(-1));
        return lastAttendedResults(user, weekStart, weekEnd, batch);
    }

    public Optional<LastAttendedResult> singleDayAttendedResults(User user, Batch batch, Date from, Date to){
        return lastAttendedResults(user, from, to, batch).stream().findFirst();
    }

    public List<StudentAnswer> getStudentAnswer(User user){
       List<StudentAnswer> studentAnswers = studentAnswerRepository.findByUser(user);
       studentAnswers.sort((s1,s2)->(s1.getQuestion().getQuestionNo()-s2.getQuestion().getQuestionNo()));

       return studentAnswers;
    }

    public List<StudentAnswer> getStudentAnswerByUserIdAndBatchId(long userId, long batchId){
       List<StudentAnswer> studentAnswers = studentAnswerRepository.findStudentAnswerByUserIdAndBatchId(userId, batchId);
        sortStudentAnswers(studentAnswers);
        return studentAnswers;
    }

    public List<StudentAnswer> getStudentAnswerByUserIdAndBatchIdAndSubject(long userId, long batchId, String subjectName){
       Subject subject = subjectRepository.findByName(subjectName);
       if(subject==null){
           // need to throw exception for subject not found in future.
       }
       List<StudentAnswer> studentAnswers = studentAnswerRepository.findStudentAnswerByUserIdAndBatchIdAndQuestionSubjectId(userId,batchId,subject.getId());
        sortStudentAnswers(studentAnswers);

        return studentAnswers;
    }

    private void sortStudentAnswers(List<StudentAnswer> studentAnswers) {
        studentAnswers.sort((s1,s2)->{
            int ans =  s1.getQuestion().getQuestionNo()-s2.getQuestion().getQuestionNo();
            if(ans==0){
                return s1.getQuestion().getYear()-s2.getQuestion().getYear();
            } else {
                return ans;
            }
        });
    }
}
