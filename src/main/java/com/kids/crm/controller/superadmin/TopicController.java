package com.kids.crm.controller.superadmin;

import com.kids.crm.model.SubTopic;
import com.kids.crm.model.Subject;
import com.kids.crm.model.Topic;
import com.kids.crm.service.SubTopicService;
import com.kids.crm.service.SubjectService;
import com.kids.crm.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/superadmin/topic")
public class TopicController {
    @Autowired
    TopicService topicService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    SubTopicService subTopicService;

    @GetMapping(value={"","/"})
    public String topicPage(ModelMap modelMap) {
        List<Topic> topics = topicService.getAllTopic();
        modelMap.addAttribute("topics", topics);
        return "super/topic";
    }
    @GetMapping("/add")
    public String addTopic(ModelMap modelMap) {
        Topic topic = new Topic();
        modelMap.addAttribute("topic",topic);
        List<Subject> subjects = subjectService.getSubjects();
        modelMap.addAttribute("subjects",subjects);
        return "super/add-topic";
    }

    @PostMapping("/save")
    public String saveTopic(@ModelAttribute Topic form) {
        Topic topic = populateFormData(form);
        topicService.saveTopic(topic);
        return "redirect:/superadmin/topic";
    }

    @GetMapping("/{topicId}/edit")
    public String editTopic(ModelMap modelMap, @PathVariable(value="topicId") String topicId) {
        if (!topicId.matches("\\d+")){
            throw new IllegalArgumentException();
        }
        Topic topic = topicService.findTopicByTopicId(Long.parseLong(topicId));
        modelMap.addAttribute("topic",topic);

        List<Subject> subjects = subjectService.getSubjects();
        modelMap.addAttribute("subjects",subjects);

        List<SubTopic> subTopics = subTopicService.getSubTopicsByTopicId(Long.parseLong(topicId));
        modelMap.addAttribute("subTopics",subTopics);
        return "super/add-topic";
    }

    private Topic populateFormData(Topic form){
        Topic topic;
        if(form.getId()!=null){
            topic = topicService.findTopicByTopicId(form.getId());
        } else{
            topic = new Topic();
        }
        if (form.getName()!=null){
            topic.setName(form.getName());
        }
        if (form.getSubject()!=null){
            Subject subject = new Subject();
            subject.setId(form.getSubject().getId());
            topic.setSubject(subject);
        }
        return topic;
    }
}
