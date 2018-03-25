package com.kids.crm.service;

import com.kids.crm.model.QQuestion;
import com.kids.crm.model.Question;
import com.kids.crm.pojo.ExamSettingsDTO;
import com.kids.crm.repository.QuestionRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Iterable<Question> findQuestionsByExamSettings(long subjectId, ExamSettingsDTO examSettingsDTO){
        BooleanExpression predicate = QQuestion.question.subject.id.eq(subjectId);
        if(examSettingsDTO.getTopicId() > 0){
            predicate.and(QQuestion.question.topic.id.eq(examSettingsDTO.getTopicId()));
        }
        if(examSettingsDTO.getYear() > 0){
            predicate.and(QQuestion.question.year.eq(examSettingsDTO.getYear()));
        }

        Iterable<Question> questions;
        if(predicate == null){
            return questionRepository.findAll();
        } else {
            return questionRepository.findAll(predicate);
        }

    }
}
