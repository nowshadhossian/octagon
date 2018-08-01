package com.kids.crm.service;

import com.kids.crm.model.*;
import com.kids.crm.pojo.LastAttendedResult;
import com.kids.crm.repository.*;
import com.kids.crm.utils.Constants;
import com.kids.crm.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentAnswerRepository studentAnswerRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentBatchRepository studentBatchRepository;
    private final SubjectRepository subjectRepository;
    private final GuardianRepository guardianRepository;
    private final MailSender mailSender;
    private final StudentBatchInterestRepository studentBatchInterestRepository;
    private final StudentRefereeRepository studentRefereeRepository;

    @Autowired private BatchService batchService;

    @Autowired
    public StudentService(StudentRepository repository, StudentAnswerRepository studentAnswerRepository, PasswordEncoder passwordEncoder, StudentBatchRepository studentBatchRepository, SubjectRepository subjectRepository, GuardianRepository guardianRepository, MailSender mailSender, StudentBatchInterestRepository studentBatchInterestRepository, StudentRefereeRepository studentRefereeRepository) {
        this.repository = repository;
        this.studentAnswerRepository = studentAnswerRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentBatchRepository = studentBatchRepository;
        this.subjectRepository = subjectRepository;
        this.guardianRepository = guardianRepository;
        this.mailSender = mailSender;
        this.studentBatchInterestRepository = studentBatchInterestRepository;
        this.studentRefereeRepository = studentRefereeRepository;
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


    public User registerStudent(Signup signup) throws ParseException {
           Student student = Student.builder()
                   .address(signup.getAddress())
                   .phone(signup.getPhoneNo())
                   .dateOfBirth(Constants.df.parse(signup.getDateOfBirth()))
                   .gender(signup.getGender())
                   .school(signup.getSchool())
                   .examsCurriculum(StringUtils.removeEnd(signup.getExamsCurriculum(), ","))
                   .referral(signup.getReferral())
                   .version(Version.getById(signup.getVersion()))
                   .build();
           student.setFirstName(signup.getFirstName());
           student.setLastName(signup.getLastName());
           student.setEmail(signup.getEmail());
           student.setPassword(passwordEncoder.encode(signup.getPassword()));

           Student studentSaved = save(student);

           for (int i = 0; i < signup.getGuardianEmail().length; i++) {
               Guardian guardian = Guardian.builder()
                       .email(signup.getGuardianEmail()[i])
                       .phone(signup.getGuardianContactNo()[i])
                       .relation(signup.getGuardianRelation()[i])
                       .name(signup.getGuardianName()[i])
                       .student(studentSaved)
                       .build();
               guardianRepository.save(guardian);
           }

           for (int i = 0; i < signup.getEnrollingIds().length; i++) {
               Batch batch = batchService.findOrCreateBySessionIdAndSubjectIdAndTeacherId(signup.getInterestSessionId(), signup.getEnrollingIds()[i], signup.getTeacherId());

               StudentBatch studentBatch = StudentBatch.builder()
                       .student(studentSaved)
                       .batch(batch)
                       .batchStatusType(BatchStatusType.PENDING)
                       .build();
               studentBatchRepository.save(studentBatch);

               StudentBatchInterest studentBatchInterest = StudentBatchInterest.builder()
                       .student(studentSaved)
                       .batch(batch)
                       .build();
               studentBatchInterestRepository.save(studentBatchInterest);
           }

           if (signup.getRefereesName() != null) {
               for (int i = 0; i < signup.getRefereesName().length; i++) {
                   StudentReferee studentReferee = StudentReferee.builder()
                           .refereeName(signup.getRefereesName()[i])
                           .student(studentSaved)
                           .subject(subjectRepository.findById(signup.getRefereesSubjectId()[i]).get())
                           .build();
                   studentRefereeRepository.save(studentReferee);
               }
           }

           mailSender.sendEmailToVerifyEmail(studentSaved);


        return studentSaved;
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

    public List<StudentAnswer> getStudentAnswerByUserIdAndBatchIdAndTopicId(long userId, long batchId, long topicId){
       List<StudentAnswer> studentAnswers = studentAnswerRepository.findStudentAnswerByUserIdAndBatchIdAndQuestionTopicId(userId,batchId,topicId);
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
