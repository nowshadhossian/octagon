package com.kids.crm.controller.superadmin;

import com.kids.crm.model.Subject;
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
    public String subjectPage(ModelMap modelMap) {
        List<Subject> subjects = subjectService.getSubjects();
        modelMap.addAttribute("subjects",subjects);
        return "super/subject";
    }
    @GetMapping("add")
    public String addSubject(ModelMap modelMap) {
        Subject subject = new Subject();
        modelMap.addAttribute("subject", subject);
        return "super/add-subject";
    }
    @PostMapping("/add")
    public String createSubject(@ModelAttribute Subject subject) {
        subjectService.insertOrUpdateSubject(subject);
        return "redirect:/superadmin/subject";
    }
}
