package com.kids.crm.service.converter;

import com.kids.crm.model.StudentBatch;
import com.kids.crm.repository.StudentBatchRepository;
import com.kids.crm.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToStudentBatchConverter implements Converter<Long, StudentBatch> {
    @Autowired
    private StudentBatchRepository studentBatchRepository;

    @Override
    public StudentBatch convert(Long source) {
        return studentBatchRepository.findById(source).orElseThrow(NotFoundException::new);
    }
}
