package com.kids.crm.repository;

import com.kids.crm.model.SubTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubTopicRepository extends JpaRepository<SubTopic, Long> {


    Optional<SubTopic> findByName(String name);
    List<SubTopic> findSubTopicsByTopicId(Long topicId);

}
