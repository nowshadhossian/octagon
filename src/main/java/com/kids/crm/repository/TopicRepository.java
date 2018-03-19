package com.kids.crm.repository;

import com.kids.crm.model.SubTopic;
import com.kids.crm.model.Subject;
import com.kids.crm.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findByNameAndSubject(String name, Subject subject);
}
