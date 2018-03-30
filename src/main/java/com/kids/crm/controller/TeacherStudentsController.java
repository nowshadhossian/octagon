package com.kids.crm.controller;

import com.kids.crm.model.Batch;
import com.kids.crm.model.Student;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.StudentRepository;
import com.kids.crm.repository.TeacherRepository;
import com.kids.crm.service.BatchService;
import com.kids.crm.service.TeacherService;
import com.kids.crm.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class TeacherStudentsController {
    private final static String BASE_ROUTE = "/teacher/students";
    private final static String MY_STUDENTS_ROUTE = BASE_ROUTE + "/mine";
    private final static String ADD_STUDENTS = BASE_ROUTE + "/add";

    private final UserSession userSession;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final BatchService batchService;
    private final TeacherService teacherService;
    private final StudentAnswerRepository studentAnswerRepository;

    @Autowired
    public TeacherStudentsController(UserSession userSession, TeacherRepository teacherRepository, StudentRepository studentRepository, BatchService batchService, TeacherService teacherService, StudentAnswerRepository studentAnswerRepository) {
        this.userSession = userSession;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.batchService = batchService;
        this.teacherService = teacherService;
        this.studentAnswerRepository = studentAnswerRepository;
    }


    @RequestMapping(value = MY_STUDENTS_ROUTE, method = RequestMethod.GET)
    public String getMyStudentsPage(ModelMap modelMap){
        userSession.setCurrentBatch(batchService.reFetch(userSession.getCurrentBatch()));
        modelMap.addAttribute("students", userSession.getCurrentBatch().getStudents());
        modelMap.addAttribute("batch", userSession.getCurrentBatch());
        return "/teacher/my-students";
    }

    @RequestMapping(value = ADD_STUDENTS, method = RequestMethod.GET)
    public String getAddStudentsPage(ModelMap modelMap){
        userSession.setCurrentBatch(batchService.reFetch(userSession.getCurrentBatch()));

        List<Student> students = studentRepository.findAll();
        students.removeAll(userSession.getCurrentBatch().getStudents());

        modelMap.addAttribute("students", students);
        modelMap.addAttribute("batch", userSession.getCurrentBatch());
        return "/teacher/all-students";
    }

    @RequestMapping(value = ADD_STUDENTS, method = RequestMethod.POST)
    public String addStudentsPage(ModelMap modelMap, Student student){

        student.addToBatch(userSession.getCurrentBatch());
        studentRepository.save(student);

        return "redirect:" + ADD_STUDENTS;
    }

    @RequestMapping(value = "/teacher/students/remove", method = RequestMethod.POST)
    public String removeStudent(ModelMap modelMap, Student student){
        teacherService.removeStudentFromBatch(student.getId(), userSession.getCurrentBatch().getId());
        return "redirect:" + MY_STUDENTS_ROUTE;
    }
}
