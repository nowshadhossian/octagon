package com.kids.crm.mongo.repository;

import com.kids.crm.model.mongo.UserLoginSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserLoginSessionRepository extends MongoRepository<UserLoginSession, Long>{
}
