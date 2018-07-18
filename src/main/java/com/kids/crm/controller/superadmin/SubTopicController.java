package com.kids.crm.controller.superadmin;

import com.kids.crm.model.SubTopic;
import com.kids.crm.model.Topic;
import com.kids.crm.service.SubTopicService;
import com.kids.crm.service.TopicService;
import com.kids.crm.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/superadmin/sub-topic")
public class SubTopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    SubTopicService subTopicService;

    @GetMapping("/add")
    public String addSubTopic(@RequestParam("topicId") String topicId, ModelMap modelMap){
        if (!topicId.matches("\\d+")){
            throw new IllegalArgumentException();
        }
        Topic topic = topicService.findTopicByTopicId(Long.parseLong(topicId));
        modelMap.addAttribute("topic",topic);
        modelMap.addAttribute("subTopic",new SubTopic());
        return "super/add-sub-topic";
    }
    @GetMapping("/{subTopicId}/edit")
    public String editSubTopic(@PathVariable(value="subTopicId") String subTopicId,ModelMap modelMap){
        if (!subTopicId.matches("\\d+")){
            throw new IllegalArgumentException();
        }
        Optional<SubTopic> subTopic = subTopicService.getSubTopicById(Long.parseLong(subTopicId));
        if (!(subTopic.isPresent())){
            throw new NotFoundException("Sub-Topic not found");
        }
        modelMap.addAttribute("subTopic",subTopic.get());
        return "super/add-sub-topic";
    }
    @PostMapping("/save")
    public String saveSubTopic(@ModelAttribute SubTopic subTopic) {
        subTopicService.saveSubTopic(subTopic);
        return "redirect:/superadmin/topic/" + subTopic.getTopic().getId() + "/edit";
    }

    @GetMapping("/getSubtopic")
    @ResponseBody
    public List<SubTopic> getSubtopicByTopicId(@RequestParam(value = "topicId") long topicId){
        List<SubTopic> subTopics = subTopicService.getSubTopicsByTopicId(topicId);
        return subTopics;
    }
}
