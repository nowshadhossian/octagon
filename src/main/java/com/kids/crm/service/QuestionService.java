package com.kids.crm.service;

import com.kids.crm.model.QQuestion;
import com.kids.crm.model.Question;
import com.kids.crm.pojo.ExamSettingsDTO;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.service.exception.NotFoundException;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Iterable<Question> findQuestionsByExamSettings(long subjectId, ExamSettingsDTO examSettingsDTO) {
        BooleanExpression predicate = QQuestion.question.subject.id.eq(subjectId);
        if (examSettingsDTO.getTopicId() > 0) {
            predicate = predicate.and(QQuestion.question.topic.id.eq(examSettingsDTO.getTopicId()));
        }
        if (examSettingsDTO.getYear() > 0) {
            predicate = predicate.and(QQuestion.question.year.eq(examSettingsDTO.getYear()));
        }

        if (predicate == null) {
            return questionRepository.findAll();
        } else {
            return questionRepository.findAll(predicate);
        }

    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }
    public Question getQuestionById(Long questionId){
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (!(questionOptional.isPresent())){
            throw new NotFoundException("Topic not found");
        }
        return questionOptional.get();
    }

    public String generateQuestionName(Question question,String ext) {
        String name = question.getYear()+question.getSession().getId().toString()+question.getSubject().getId().toString()+question.getQuestionNo()+"."+ext;
        return name;
    }
}
