package com.kids.crm.controller;

import com.kids.crm.model.Batch;
import com.kids.crm.model.Teacher;
import com.kids.crm.model.User;
import com.kids.crm.repository.TeacherRepository;
import com.kids.crm.service.UserSession;
import com.kids.crm.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherSwitchBatchController {
    private UserSession userSession;
    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherSwitchBatchController(UserSession userSession, TeacherRepository teacherRepository) {
        this.userSession = userSession;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping(value = "/teacher/switch-batch")
    public String getSwitchBatchPage(ModelMap modelMap){
        User user = userSession.getLoggedInUser();
        Teacher teacher = teacherRepository.findById(user.getId()).orElseThrow(new UserNotFoundException(user.getId()));
        modelMap.addAttribute("myBatches", teacher.getBatches());
        return "/teacher/switch-batch";
    }

    @GetMapping(value = "/teacher/batch/{batch}")
    public String activateBatchPage(ModelMap modelMap, Batch batch){
        userSession.setCurrentBatch(batch);
        return "redirect:/teacher/dashboard";
    }


}
