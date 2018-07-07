package com.kids.crm.controller.superadmin;

import com.kids.crm.model.Subject;
import com.kids.crm.model.Topic;
import com.kids.crm.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/superadmin/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping(value = {"","/"})
    public String subjectPage(@ModelAttribute("model") ModelMap modelMap) {
        List<Subject> subjectList = subjectService.getSubjects();
        modelMap.addAttribute("allSubjects",subjectList);
        return "super/subject";
    }
    @GetMapping("add-subject")
    public String addSubject(@ModelAttribute("model") ModelMap modelMap) {
        Subject subject = new Subject();
        modelMap.addAttribute("subject", subject);
        return "super/add-subject";
    }
    @PostMapping("/add-subject")
    public String createSubject(@ModelAttribute Subject subject) {
        subjectService.insertOrUpdateSubject(subject);
        return "redirect:/superadmin/subject";
    }
}
