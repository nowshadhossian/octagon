package com.kids.crm.controller.superadmin;

import com.kids.crm.model.*;
import com.kids.crm.service.*;
import com.kids.crm.service.fileupload.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/superadmin/questions")
public class QuestionUploadController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StorageService uploadService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SubTopicService subTopicService;

    @GetMapping(value = {"","/"})
    public String questionsList(ModelMap modelMap){
        List<Question> allQuestions = questionService.getAllQuestions();
        List<SubTopic> allSubTopic = subTopicService.getAllSubTopic();
        modelMap.addAttribute("allQuestions",allQuestions);
        modelMap.addAttribute("allSubTopic",allSubTopic);
        return "super/questions";
    }

    @GetMapping("/upload")
    public String getQuestionUploadPage(ModelMap modelMap) {
        modelMap.addAttribute("question",new Question());
        List<Topic> allTopic = topicService.getAllTopic();
        List<Subject> allSubject = subjectService.getSubjects();
        List<Session> allSession = sessionService.getAllSessions();
        modelMap.addAttribute("allTopic",allTopic);
        modelMap.addAttribute("allSubject",allSubject);
        modelMap.addAttribute("allSession",allSession);
        return "super/question-upload";
    }
    @PostMapping("/save")
    public String uploadQuestionWithInfo(@ModelAttribute Question question,@RequestParam("file") MultipartFile file) {
        String fileName = questionService.generateQuestionName(question,FilenameUtils.getExtension(file.getOriginalFilename()));
        question.setFileName(fileName);
        question.setUploadDate(new Date());
        questionService.saveQuestion(question);

        uploadService.store(file,fileName);
        return "redirect:/superadmin/questions";
    }
    @GetMapping("/{questionId}/edit")
    public String editQuestion(ModelMap modelMap,@PathVariable(value = "questionId") String questionId){
        if (!questionId.matches("\\d+")){
            throw new IllegalArgumentException();
        }
        Question question = questionService.getQuestionById(Long.parseLong(questionId));
        modelMap.addAttribute("question",question);
        List<Topic> allTopic = topicService.getAllTopic();
        List<Subject> allSubject = subjectService.getSubjects();
        List<Session> allSession = sessionService.getAllSessions();
        modelMap.addAttribute("allTopic",allTopic);
        modelMap.addAttribute("allSubject",allSubject);
        modelMap.addAttribute("allSession",allSession);
        return "super/question-upload";
    }
}
