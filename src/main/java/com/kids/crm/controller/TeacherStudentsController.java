package com.kids.crm.controller;

import com.kids.crm.model.BatchStatusType;
import com.kids.crm.model.Student;
import com.kids.crm.model.StudentBatch;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.StudentBatchRepository;
import com.kids.crm.repository.StudentRepository;
import com.kids.crm.repository.TeacherRepository;
import com.kids.crm.service.BatchService;
import com.kids.crm.service.TeacherService;
import com.kids.crm.service.UserSession;
import com.kids.crm.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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
    private final StudentBatchRepository studentBatchRepository;

    @Autowired
    public TeacherStudentsController(UserSession userSession, TeacherRepository teacherRepository, StudentRepository studentRepository, BatchService batchService, TeacherService teacherService, StudentAnswerRepository studentAnswerRepository, StudentBatchRepository studentBatchRepository) {
        this.userSession = userSession;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.batchService = batchService;
        this.teacherService = teacherService;
        this.studentAnswerRepository = studentAnswerRepository;
        this.studentBatchRepository = studentBatchRepository;
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

/*        List<Student> students = userSession.getCurrentBatch().getStudents();
      //  students.removeAll(userSession.getCurrentBatch().getStudents());*/

        modelMap.addAttribute("studentBatches",  userSession.getCurrentBatch().getStudentBatches());
        modelMap.addAttribute("batch", userSession.getCurrentBatch());
        return "/teacher/all-students";
    }

    @RequestMapping(value = ADD_STUDENTS, method = RequestMethod.POST)
    public String addStudentsPage(ModelMap modelMap, Student student){
        StudentBatch studentBatch = studentBatchRepository.findByStudentAndBatch(student, userSession.getCurrentBatch()).stream().findFirst().orElseThrow(NotFoundException::new);
        studentBatch.setBatchStatusType(BatchStatusType.ACTIVE);

        studentBatchRepository.save(studentBatch);

        return "redirect:" + ADD_STUDENTS;
    }

    @RequestMapping(value = "/teacher/students/remove", method = RequestMethod.POST)
    public String removeStudent(ModelMap modelMap, Student student){
        teacherService.removeStudentFromBatch(student.getId(), userSession.getCurrentBatch().getId());
        return "redirect:" + MY_STUDENTS_ROUTE;
    }
}
