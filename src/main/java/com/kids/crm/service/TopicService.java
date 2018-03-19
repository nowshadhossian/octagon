package com.kids.crm.service;

import com.kids.crm.model.Subject;
import com.kids.crm.model.Topic;
import com.kids.crm.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicService {
    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic findOrCreateTopic(String topic, Subject subject){
        return topicRepository.findByNameAndSubject(topic, subject)
                .orElseGet(() -> topicRepository.save(Topic.builder().name(topic).subject(subject).build()));
    }
}
