package com.kids.crm.controller;

import com.kids.crm.model.*;
import com.kids.crm.repository.TeacherRepository;
import com.kids.crm.service.BatchService;
import com.kids.crm.service.SessionService;
import com.kids.crm.service.SubjectService;
import com.kids.crm.service.UserSession;
import com.kids.crm.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
public class TeacherSwitchBatchController {
    private UserSession userSession;
    private TeacherRepository teacherRepository;
    private SessionService sessionService;
    private SubjectService subjectService;
    private BatchService batchService;

    @Autowired
    public TeacherSwitchBatchController(UserSession userSession, TeacherRepository teacherRepository, SessionService sessionService, SubjectService subjectService, BatchService batchService) {
        this.userSession = userSession;
        this.teacherRepository = teacherRepository;
        this.sessionService = sessionService;
        this.subjectService = subjectService;
        this.batchService = batchService;
    }

    @GetMapping(value = "/teacher/switch-batch")
    public String getSwitchBatchPage(ModelMap modelMap) {
        User user = userSession.getLoggedInUser();
        Teacher teacher = teacherRepository.findById(user.getId()).orElseThrow(new UserNotFoundException(user.getId()));
        modelMap.addAttribute("myBatches", teacher.getBatches());
        return "/teacher/switch-batch";
    }

    @GetMapping(value = "/teacher/batch/{batch}")
    public String activateBatchPage(ModelMap modelMap, Batch batch) {
        userSession.setCurrentBatch(batch);
        return "redirect:/teacher/dashboard";
    }

    @GetMapping(value = "/teacher/batch/add")
    public String getAddBatchPage(ModelMap modelMap) {
        User user = userSession.getLoggedInUser();
        Teacher teacher = teacherRepository.findById(user.getId()).orElseThrow(new UserNotFoundException(user.getId()));
        LocalDate localDate = LocalDate.now();
        List<Integer> years = Arrays.asList(localDate.getYear(), localDate.getYear() + 1, localDate.getYear() + 2);
        modelMap.addAttribute("years", years);
        modelMap.addAttribute("sessions", List.of("Summer", "Spring"));
        modelMap.addAttribute("subjects", subjectService.getSubjects());

        return "/teacher/batch/add";
    }

    @PostMapping(value = "/teacher/batch/add")
    public String saveBatch(ModelMap modelMap, String session, int year, Subject subject) {
        User user = userSession.getLoggedInUser();
        Teacher teacher = teacherRepository.findById(user.getId()).orElseThrow(new UserNotFoundException(user.getId()));

        Session sessionNew = sessionService.findOrCreateSessionByNameAndYear(session, year);
        Batch batch = Batch.builder()
                .session(sessionNew)
                .subject(subject)
                .teacher(teacher)
                .build();

        batchService.save(batch);

        return "redirect:/teacher/switch-batch";
    }


}
