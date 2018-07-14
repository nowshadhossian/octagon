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
        List<Subject> subjectList = subjectService.getSubjects();
        modelMap.addAttribute("allSubject",subjectList);
        return "super/add-topic";
    }

    @PostMapping("/save")
    public String saveTopic(@ModelAttribute Topic topic) {
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

        List<Subject> subjectList = subjectService.getSubjects();
        modelMap.addAttribute("allSubject",subjectList);

        List<SubTopic> subTopicList = subTopicService.getSubTopicsByTopicId(Long.parseLong(topicId));
        List<SubTopic> subTopics = subTopicList;
        modelMap.addAttribute("subTopics",subTopics);
        return "super/add-topic";
    }

}
