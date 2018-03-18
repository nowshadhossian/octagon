package com.kids.crm.controller;

import com.kids.crm.model.*;
import com.kids.crm.repository.*;
import com.kids.crm.service.BatchService;
import com.kids.crm.service.StudentService;
import com.kids.crm.validator.SignupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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

    public static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    @Autowired
    public RegistrationController(StudentService studentService, SignupValidator validator, BatchService batchService, PasswordEncoder passwordEncoder, GuardianRepository guardianRepository, StudentBatchInterestRepository studentBatchInterestRepository, SubjectRepository subjectRepository, StudentRefereeRepository studentRefereeRepository) {
        this.studentService = studentService;
        this.validator = validator;
        this.batchService = batchService;
        this.passwordEncoder = passwordEncoder;
        this.guardianRepository = guardianRepository;
        this.studentBatchInterestRepository = studentBatchInterestRepository;
        this.subjectRepository = subjectRepository;
        this.studentRefereeRepository = studentRefereeRepository;
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
        List<Subject> upcomingSubjects = upcomingBatches.stream()
                .map(batch -> batch.getSubject())
                .collect(Collectors.toList());
        model.addAttribute("upcomingSessions", upcomingSessions);
        model.addAttribute("upcomingSubjects", upcomingSubjects);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, params = "register")
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
        }

        for (int i = 0; i < signup.getRefereesName().length; i++) {
            StudentReferee studentReferee = StudentReferee.builder()
                    .refereeName(signup.getRefereesName()[i])
                    .student(studentSaved)
                    .subject(subjectRepository.findById(signup.getRefereesSubjectId()[i]).get())
                    .build();
            studentRefereeRepository.save(studentReferee);
        }

        redirectAttributes.addFlashAttribute("successMsg", "Account created Successfully. Please contact your teacher for enrolment.");
        return "redirect:/login";
    }
}
