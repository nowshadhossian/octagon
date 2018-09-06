package com.kids.crm.controller;

import com.kids.crm.model.Student;
import com.kids.crm.model.StudentBatch;
import com.kids.crm.model.User;
import com.kids.crm.repository.StudentRepository;
import com.kids.crm.service.UserSession;
import com.kids.crm.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

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
    public String getSwitchBatchPage(ModelMap modelMap) {
        User user = userSession.getLoggedInUser();
        Student student = studentRepository.findById(user.getId()).orElseThrow(new UserNotFoundException(user.getId()));
        List<StudentBatch> studentBatches = new ArrayList<>(student.getStudentBatches());
        if (studentBatches.size() == 1 && !studentBatches.get(0).getBatchStatusType().isPending()) {
            return "redirect:/student/batch/" + studentBatches.get(0).getId();
        }
        modelMap.addAttribute("myStudentBatches", studentBatches);
        return "/student/switch-batch";
    }

    @GetMapping(value = "/student/batch/{studentBatch}")
    public String activateBatchPage(StudentBatch studentBatch, RedirectAttributes redirectAttributes) {
        if(studentBatch.getBatchStatusType().isPending()){
            redirectAttributes.addFlashAttribute("errorMsg", "The Batch State is now pending ask your teacher to activate");
            return "redirect:/student/switch-batch";
        }
        userSession.setCurrentBatch(studentBatch.getBatch());

        return "redirect:/student/dashboard/graph";
//        return "redirect:/student/dashboard";
    }
}
