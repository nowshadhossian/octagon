package com.kids.crm.controller;

import com.kids.crm.config.Config;
import com.kids.crm.model.*;
import com.kids.crm.repository.*;
import com.kids.crm.service.*;
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

import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashSet;
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
    private final MailSender mailSender;
    private final SessionService sessionService;



    @Autowired
    public RegistrationController(StudentService studentService, SignupValidator validator, BatchService batchService, PasswordEncoder passwordEncoder, GuardianRepository guardianRepository, StudentBatchInterestRepository studentBatchInterestRepository, SubjectRepository subjectRepository, StudentRefereeRepository studentRefereeRepository, Config config, TeacherService teacherService, StudentBatchRepository studentBatchRepository, MailSender mailSender, SessionService sessionService) {
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
        this.mailSender = mailSender;
        this.sessionService = sessionService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("signup", new Signup());
        addToModelMap(model);

        return "register";
    }

    private void addToModelMap(Model model) {
        //List<Batch> upcomingBatches = batchService.getUpcomingBatches();
        LocalDate localDate = LocalDate.now();
        List<Session> upcomingSessions = sessionService.getAllSessions().stream()
                .filter(session -> session.getYear() >= localDate.getYear())
                .collect(Collectors.toList());
        Set<Subject> upcomingSubjects = new HashSet<>(subjectRepository.findAll());
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

        User student = studentService.registerStudent(signup);

        redirectAttributes.addFlashAttribute("successMsg", "Account created Successfully. Please contact your teacher for enrolment.");
        return "redirect:/login";
    }

}
