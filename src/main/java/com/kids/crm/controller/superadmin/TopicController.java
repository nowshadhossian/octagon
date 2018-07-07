package com.kids.crm.controller.superadmin;

import com.kids.crm.model.Subject;
import com.kids.crm.model.Topic;
import com.kids.crm.repository.TopicRepository;
import com.kids.crm.service.SubjectService;
import com.kids.crm.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/superadmin/topic")
public class TopicController {
    @Autowired
    TopicService topicService;
    @Autowired
    SubjectService subjectService;

    @GetMapping(value={"","/"})
    public String topicPage(@ModelAttribute("model") ModelMap model) {
        List<Topic> topics = topicService.getAllTopic();
        model.addAttribute("topics", topics);
        model.addAttribute("key","value");
        return "super/topic";
    }

    @GetMapping("/add")
    public String addTopic(@ModelAttribute("model") ModelMap modelMap) {
        Topic topic = new Topic();
        List<Subject> subjectList = subjectService.getSubjects();
        modelMap.addAttribute("topic",topic);
        modelMap.addAttribute("allSubject",subjectList);
        return "super/add-topic";
    }

    @PostMapping("/add")
    public String createTopic(@ModelAttribute Topic topic) {
        topicService.insertTopic(topic);
        return "redirect:/superadmin/topic";
    }
}
