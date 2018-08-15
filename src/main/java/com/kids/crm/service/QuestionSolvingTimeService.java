package com.kids.crm.service;

import com.kids.crm.model.mongo.QuestionSolvingTime;
import com.kids.crm.mongo.repository.QuestionSolvingTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionSolvingTimeService {
    private final QuestionSolvingTimeRepository questionSolvingTimeRepository;
    @Autowired
    public QuestionSolvingTimeService(QuestionSolvingTimeRepository questionSolvingTimeRepository){
        this.questionSolvingTimeRepository=questionSolvingTimeRepository;
    }

    public List<QuestionSolvingTime> getQuestionSolvingTimeByQustionId(Long questionId){
        return questionSolvingTimeRepository.findQuestionSolvingTimeByQuestionId(questionId);
    }

    public List<QuestionSolvingTime> getQuestionSolvingTimeByUserId(Long userId){
        return getQuestionSolvingTimeByUserId(userId);
    }
}
