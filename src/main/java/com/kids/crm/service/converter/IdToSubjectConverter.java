package com.kids.crm.service.converter;

import com.kids.crm.model.Student;
import com.kids.crm.model.Subject;
import com.kids.crm.repository.SubjectRepository;
import com.kids.crm.service.exception.SubjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToSubjectConverter implements Converter<Long, Subject> {
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public Subject convert(Long source) {
        return subjectRepository.findById(source).orElseThrow(SubjectNotFoundException::new);
    }
}
