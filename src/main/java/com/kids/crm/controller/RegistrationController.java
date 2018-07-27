package com.kids.crm.controller;

import com.kids.crm.config.Config;
import com.kids.crm.model.*;
import com.kids.crm.repository.*;
import com.kids.crm.service.BatchService;
import com.kids.crm.service.StudentService;
import com.kids.crm.service.TeacherService;
import com.kids.crm.service.exception.BatchNotFoundException;
import com.kids.crm.validator.SignupValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {


    private final StudentService studentService;
    private final SignupValidator validator;
    private final BatchService batchService;
    private final PasswordEncoder passwordEncoder;
    private final GuardianRepository guardianRepository;
    private final StudentBatchInterestRepository studentBatchInterestRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRefereeRepository studentRefereeRepository;
    private final Config config;
    private final TeacherService teacherService;
    private final StudentBatchRepository studentBatchRepository;

    public static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    @Autowired
    public RegistrationController(StudentService studentService, SignupValidator validator, BatchService batchService, PasswordEncoder passwordEncoder, GuardianRepository guardianRepository, StudentBatchInterestRepository studentBatchInterestRepository, SubjectRepository subjectRepository, StudentRefereeRepository studentRefereeRepository, Config config, TeacherService teacherService, StudentBatchRepository studentBatchRepository) {
        this.studentService = studentService;
        this.validator = validator;
        this.batchService = batchService;
        this.passwordEncoder = passwordEncoder;
        this.guardianRepository = guardianRepository;
        this.studentBatchInterestRepository = studentBatchInterestRepository;
        this.subjectRepository = subjectRepository;
        this.studentRefereeRepository = studentRefereeRepository;
        this.config = config;
        this.teacherService = teacherService;
        this.studentBatchRepository = studentBatchRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("signup", new Signup());
        addToModelMap(model);

        return "register";
    }

    private void addToModelMap(Model model) {
        List<Batch> upcomingBatches = batchService.getUpcomingBatches();
        List<Session> upcomingSessions = upcomingBatches.stream()
                .map(Batch::getSession)
                .collect(Collectors.toList());
        Set<Subject> upcomingSubjects = upcomingBatches.stream()
                .map(batch -> batch.getSubject())
                .collect(Collectors.toSet());
        model.addAttribute("upcomingSessions", upcomingSessions);
        model.addAttribute("upcomingSubjects", upcomingSubjects);
        model.addAttribute("isPerCourseReferral", config.getStudent().isPerCourseReferral());
        model.addAttribute("teachers", teacherService.findAll());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, params = "register")
    @Transactional
    public String registerProcess(Model model, @ModelAttribute Signup signup, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws ParseException {
        validator.validate(signup, bindingResult);
        if (bindingResult.hasErrors()) {
            addToModelMap(model);
            return "register";
        }

        Student student = Student.builder()
                .address(signup.getAddress())
                .phone(signup.getPhoneNo())
                .dateOfBirth(df.parse(signup.getDateOfBirth()))
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

        Student studentSaved = studentService.save(student);

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

            StudentBatchInterest studentBatchInterest = StudentBatchInterest.builder()
                    .student(studentSaved)
                    .batch(batchService.findBySessionIdAndSubjecId(signup.getInterestSessionId(), signup.getEnrollingIds()[i]))
                    .build();
            studentBatchInterestRepository.save(studentBatchInterest);

            StudentBatch studentBatch = StudentBatch.builder()
                    .student(studentSaved)
                    .batch(batchService.findBySessionIdAndSubjectIdAndTeacherId(signup.getInterestSessionId(), signup.getEnrollingIds()[i], signup.getTeacherId()).orElseThrow(BatchNotFoundException::new))
                    .batchStatusType(BatchStatusType.PENDING)
                    .build();
            studentBatchRepository.save(studentBatch);
        }

        if(signup.getRefereesName() != null){
            for (int i = 0; i < signup.getRefereesName().length; i++) {
                StudentReferee studentReferee = StudentReferee.builder()
                        .refereeName(signup.getRefereesName()[i])
                        .student(studentSaved)
                        .subject(subjectRepository.findById(signup.getRefereesSubjectId()[i]).get())
                        .build();
                studentRefereeRepository.save(studentReferee);
            }
        }


        redirectAttributes.addFlashAttribute("successMsg", "Account created Successfully. Please contact your teacher for enrolment.");
        return "redirect:/login";
    }
}
