package com.kids.crm.mongo.repository;

import com.kids.crm.model.mongo.QuestionSolvingTime;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestionSolvingTimeRepository extends MongoRepository<QuestionSolvingTime, Long>{

    public List<QuestionSolvingTime> findQuestionSolvingTimeByQuestionId(Long questionId);
}
