package com.kids.crm.service.converter;

import com.kids.crm.model.Student;
import com.kids.crm.repository.StudentRepository;
import com.kids.crm.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToStudentConverter implements Converter<Long, Student>{
    @Autowired private StudentRepository studentRepository;


    @Override
    public Student convert(Long source) {
        return studentRepository.findById(source).orElseThrow(() -> new UserNotFoundException(source));
    }
}
