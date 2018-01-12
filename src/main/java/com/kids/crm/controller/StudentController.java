package com.kids.crm.controller;

import com.kids.crm.model.Student;
import com.kids.crm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    private void saveStudent(){
        studentService.saveStudent("Tom", 43);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    private Student find(){
        return studentService.findByName("Tom");
    }
}
