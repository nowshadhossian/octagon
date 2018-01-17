package com.kids.crm.service;

import com.kids.crm.model.Student;
import com.kids.crm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {
    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void saveStudent(String name, int age) {
        Student student = new Student();
        student.setAge(age);
        student.setName(name);

        repository.save(student);
    }

    public Student findByName(String name){
        return repository.findByName(name).stream().findFirst().orElse(new Student());
    }

    public List<Student> findAllStudent() {
        return repository.findAll();
    }
}
