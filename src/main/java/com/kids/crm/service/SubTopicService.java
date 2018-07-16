package com.kids.crm.service;

import com.kids.crm.model.SubTopic;
import com.kids.crm.repository.SubTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SubTopicService {
    private final SubTopicRepository subTopicRepository;

    @Autowired
    public SubTopicService(SubTopicRepository subTopicRepository) {
        this.subTopicRepository = subTopicRepository;
    }

    public void saveSubTopic(SubTopic subTopic) {
        subTopicRepository.save(subTopic);
    }

    public List<SubTopic> getAllSubTopic() {
        return subTopicRepository.findAll();
    }

    public Optional<SubTopic> getSubTopicById(Long id) {
        return subTopicRepository.findById(id);
    }

    public List<SubTopic> getSubTopicsByTopicId(Long topicId) {
        return subTopicRepository.findSubTopicsByTopicId(topicId);
    }
}
