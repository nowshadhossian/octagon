package com.kids.crm.service;

import com.kids.crm.model.Batch;
import com.kids.crm.model.Question;
import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.User;
import com.kids.crm.repository.StudentAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentAnswerService {
    private final StudentAnswerRepository studentAnswerRepository;

    @Autowired
    public StudentAnswerService(StudentAnswerRepository studentAnswerRepository) {
        this.studentAnswerRepository = studentAnswerRepository;
    }

    public Optional<StudentAnswer> findByUserAndBatchAndQuestion(User user, Batch batch, Question question){
        return studentAnswerRepository.findByUserAndBatchAndQuestion(user, batch, question);
    }

}
