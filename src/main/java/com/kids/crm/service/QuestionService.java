package com.kids.crm.service;

import com.kids.crm.model.*;
import com.kids.crm.pojo.ExamSettingsDTO;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.service.exception.NotFoundException;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        if(examSettingsDTO.getQuestionNo() > 0){
            predicate = predicate.and(QQuestion.question.questionNo.eq(examSettingsDTO.getQuestionNo()));
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

    public List<Question> getAllQuestions(Version version) {
        return getAllQuestions().stream()
                .filter(question -> question.getVersion() == version.getId())
                .collect(Collectors.toList());
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

    public List<Question> getQuestionsByYear(int year){
        return questionRepository.findQuestionsByYear(year);
    }
    public String generateQuestionName(Question question,String ext) {
        return question.getSession().getYear() + "-" + question.getSession().getId().toString() + "-" + question.getSubject().getId().toString() + "-" + question.getQuestionNo() + UUID.randomUUID() + "." + ext;
    }

    public List<Question> findByVersionAndSubject(Version version, Subject subject) {
        return questionRepository.findAll().stream()
                .filter(question -> Objects.equals(version.getId(), question.getVersion()))
                .filter(question -> Objects.equals(subject.getId(), question.getSubject().getId()))
                .collect(Collectors.toList());
    }
}
