package com.kids.crm.service;

import com.kids.crm.model.mongo.QuestionStats;
import com.kids.crm.mongo.repository.QuestionStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionStatService {
    private final QuestionStatRepository questionStatRepository;

    @Autowired
    public QuestionStatService(QuestionStatRepository questionStatRepository) {
        this.questionStatRepository = questionStatRepository;
    }

    public Optional<QuestionStats> findQuestionStatById(long questionId) {
        return questionStatRepository.findById(questionId);
    }

    public QuestionStats save(QuestionStats questionStats){
        return questionStatRepository.save(questionStats);
    }
}
