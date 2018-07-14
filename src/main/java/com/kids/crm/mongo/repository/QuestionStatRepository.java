package com.kids.crm.mongo.repository;

import com.kids.crm.model.mongo.QuestionStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionStatRepository extends MongoRepository<QuestionStats, Long> {
}
