package com.kids.crm.service;

import com.kids.crm.model.Subject;
import com.kids.crm.model.Topic;
import com.kids.crm.repository.TopicRepository;
import com.kids.crm.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public List<Topic> getAllTopic() {
        return topicRepository.findAll();
    }
    public Topic findTopicByTopicId(long topicId) {
        Optional<Topic> topic = topicRepository.findById(topicId);
        if (!(topic.isPresent())){
            throw new NotFoundException("Topic not found");
        }
        return topic.get();
    }
    public void saveTopic(Topic topic) {
        topicRepository.save(topic);
    }
}
