package com.kids.crm.controller.superadmin;

import com.kids.crm.model.*;
import com.kids.crm.model.mongo.QuestionSolvingTime;
import com.kids.crm.model.mongo.QuestionStats;
import com.kids.crm.service.*;
import com.kids.crm.service.fileupload.StorageService;
import com.kids.crm.utils.Constants;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private QuestionStatService questionStatService;
    @Autowired
    private QuestionSolvingTimeService questionSolvingTimeService;

    @GetMapping(value = {"","/"})
    public String questionsList(ModelMap modelMap){
        List<Question> questions = questionService.getAllQuestions();
//        List<SubTopic> subTopics = subTopicService.getAllSubTopic();
        modelMap.addAttribute("questions",questions);
//        modelMap.addAttribute("topics",subTopics);
        return "super/questions";
    }

    @GetMapping("/upload")
    public String getQuestionUploadPage(ModelMap modelMap) {
        modelMap.addAttribute("question",new Question());
        List<Topic> topics = topicService.getAllTopic();
        List<Subject> subjects = subjectService.getSubjects();
        List<Session> sessions = sessionService.getAllSessions();
        modelMap.addAttribute("topics",topics);
        modelMap.addAttribute("subjects",subjects);
        modelMap.addAttribute("sessions",sessions);
        List<SubTopic> subTopics = subTopicService.getAllSubTopic();
        modelMap.addAttribute("subTopics",subTopics);
        return "super/question-upload";
    }
    @PostMapping("/save")
    public String uploadQuestionWithInfo(@ModelAttribute Question form,@RequestParam("file") MultipartFile file) {
        Question question = populateFormData(form,file);
        questionService.saveQuestion(question);
        if (!file.isEmpty()){
            uploadService.store(file,question.getFileName());
        }

        return "redirect:/superadmin/questions";
    }
    @GetMapping("/{questionId}/edit")
    public String editQuestion(ModelMap modelMap,@PathVariable(value = "questionId") String questionId){
        if (!questionId.matches("\\d+")){
            throw new IllegalArgumentException();
        }
        Long qId = Long.parseLong(questionId);
        Question question = questionService.getQuestionById(qId);
        modelMap.addAttribute("question",question);
        List<Topic> topics = topicService.getAllTopic();
        List<Subject> subjects = subjectService.getSubjects();
        List<Session> sessions = sessionService.getAllSessions();
        modelMap.addAttribute("topics",topics);
        modelMap.addAttribute("subjects",subjects);
        modelMap.addAttribute("sessions",sessions);
        List<SubTopic> subTopics = subTopicService.getSubTopicsByTopicId(question.getTopic().getId());
        modelMap.addAttribute("subTopics",subTopics);

        Optional<QuestionStats> questionStats = questionStatService.findQuestionStatByQuestionId(qId);
        if(questionStats.isPresent()){
            modelMap.addAttribute("questionStats",questionStats.get());
        }

        List<QuestionSolvingTime> questionSolvingTimes = questionSolvingTimeService.getQuestionSolvingTimeByQustionId(qId);
        Integer totalAnswerDuration=0;
        for (QuestionSolvingTime questionSolvingTime: questionSolvingTimes) {
            if(questionSolvingTime.equals(Constants.ANSWERED)){
                totalAnswerDuration+=questionSolvingTime.getDuration();
            }
        }

        modelMap.addAttribute("totalAnswerDuration",totalAnswerDuration);
        return "super/question-upload";
    }

    private Question populateFormData(Question form, MultipartFile file){
        Question question;
        if (form.getId()!=null){
            question = questionService.getQuestionById(form.getId());
        } else {
            question = new Question();
        }

        if(!file.isEmpty()){
            String fileName = questionService.generateQuestionName(form,FilenameUtils.getExtension(file.getOriginalFilename()));
            question.setFileName(fileName);
        }

        question.setAnswer(form.getAnswer());
        question.setSession(form.getSession());
        question.setYear(form.getYear());
        question.setQuestionNo(form.getQuestionNo());
        question.setCurriculum(form.getCurriculum());
        question.setActive(form.isActive());
        question.setVariant(form.getVariant());
        question.setTopic(form.getTopic());
        question.setPaper(form.getPaper());
        question.setSubject(form.getSubject());
        question.setAnswerExplanation(form.getAnswerExplanation());
        question.setUploadDate(new Date());
        question.setSubTopics(form.getSubTopics());
        question.setVersion(form.getVersion());
        return question;
    }
}
