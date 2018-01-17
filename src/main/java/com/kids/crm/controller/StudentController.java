package com.kids.crm.controller;

import com.kids.crm.model.Student;
import com.kids.crm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody
    private void saveStudent(){
        studentService.saveStudent("Tom", 43);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    private Student find(){
        return studentService.findByName("Tom");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private String showsList(Model model){
        model.addAttribute("studentList", studentService.findAllStudent());
        model.addAttribute("msg", "Introduction of Freemarker in Project");
        return "studentlist";
    }


    @RequestMapping(value = "/success", method = RequestMethod.GET)
    @ResponseBody
    private String success(){
        return "success";
    }

    @RequestMapping(value = "/fail", method = RequestMethod.GET)
    @ResponseBody
    private String fail(){
        return "fail";
    }
}
