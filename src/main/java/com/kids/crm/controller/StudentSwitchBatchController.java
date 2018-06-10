package com.kids.crm.controller;

import com.kids.crm.model.Batch;
import com.kids.crm.model.Student;
import com.kids.crm.model.Teacher;
import com.kids.crm.model.User;
import com.kids.crm.repository.StudentRepository;
import com.kids.crm.service.UserSession;
import com.kids.crm.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentSwitchBatchController {

    private UserSession userSession;
    private StudentRepository studentRepository;

    @Autowired
    public StudentSwitchBatchController(UserSession userSession, StudentRepository studentRepository) {
        this.userSession = userSession;
        this.studentRepository = studentRepository;
    }

    @GetMapping(value = "/student/switch-batch")
    public String getSwitchBatchPage(ModelMap modelMap){
        User user = userSession.getLoggedInUser();
        Student student = studentRepository.findById(user.getId()).orElseThrow(new UserNotFoundException(user.getId()));
        modelMap.addAttribute("myBatches", student.getBatches());
        return "/student/switch-batch";
    }

    @GetMapping(value = "/student/batch/{batch}")
    public String activateBatchPage( Batch batch){
        userSession.setCurrentBatch(batch);

        return "redirect:/student/dashboard";
    }
}
