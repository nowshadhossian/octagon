package com.kids.crm.mongo.repository;

import com.kids.crm.model.mongo.QuestionStats;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QuestionStatRepository extends MongoRepository<QuestionStats, Long> {
    Optional<QuestionStats> findByQuestionId(long questionId);
}
