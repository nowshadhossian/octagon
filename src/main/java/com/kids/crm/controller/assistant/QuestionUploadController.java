package com.kids.crm.controller.assistant;

import com.google.common.collect.Lists;
import com.kids.crm.config.Config;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/assistant/questions")
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
    @Autowired
    private Config config;


    @GetMapping(value = {"", "/"})
    public String questionsList(ModelMap modelMap) {
        List<Question> questions = questionService.getAllQuestions();
        modelMap.addAttribute("questions", questions);
        return "assistant/questions";
    }

    @GetMapping("/upload")
    public String getQuestionUploadPage(ModelMap modelMap) {
        modelMap.addAttribute("question", new Question());
        List<Topic> topics = topicService.getAllTopic();
        List<Subject> subjects = subjectService.getSubjects();
        List<Session> sessions = sessionService.getAllSessions();
        modelMap.addAttribute("topics", topics);
        modelMap.addAttribute("subjects", subjects);
        modelMap.addAttribute("sessions", sessions);
        modelMap.addAttribute("subTopics", Lists.newArrayList());

        addModelMap(modelMap);
        return "assistant/question-upload";
    }

    public void addModelMap(ModelMap modelMap){
        modelMap.addAttribute("curriculums", config.getCurriculums());
        modelMap.addAttribute("variants", config.getQuestionVariants());
    }

    @PostMapping("/save")
    public String uploadQuestionWithInfo(@ModelAttribute Question form, BindingResult bindingResult, @RequestParam("file") MultipartFile file) {
        Question question = populateFormData(form, file);
        questionService.saveQuestion(question);
        if (!file.isEmpty()) {
            uploadService.store(file, question.getFileName());
        }

        return "redirect:/assistant/questions";
    }

    @GetMapping("/{questionId}/edit")
    public String editQuestion(ModelMap modelMap, @PathVariable(value = "questionId") String questionId) {
        if (!questionId.matches("\\d+")) {
            throw new IllegalArgumentException();
        }
        Long qId = Long.parseLong(questionId);
        Question question = questionService.getQuestionById(qId);
        modelMap.addAttribute("question", question);

        List<Topic> topics = topicService.getAllTopic();
        List<Subject> subjects = subjectService.getSubjects();
        List<Session> sessions = sessionService.getAllSessions();
        modelMap.addAttribute("topics", topics);
        modelMap.addAttribute("subjects", subjects);
        modelMap.addAttribute("sessions", sessions);
        List<SubTopic> subTopics = subTopicService.getSubTopicsByTopicId(question.getTopic().getId());
        modelMap.addAttribute("subTopics", subTopics);

        addModelMap(modelMap);

        Optional<QuestionStats> questionStats = questionStatService.findQuestionStatByQuestionId(qId);
        if (questionStats.isPresent()) {
            modelMap.addAttribute("questionStats", questionStats.get());
        }

        List<QuestionSolvingTime> questionSolvingTimes = questionSolvingTimeService.getQuestionSolvingTimeByQustionId(qId);
        Integer totalAnswerDuration = 0;
        for (QuestionSolvingTime questionSolvingTime : questionSolvingTimes) {
            if (questionSolvingTime.equals(Constants.ANSWERED)) {
                totalAnswerDuration += questionSolvingTime.getDuration();
            }
        }

        modelMap.addAttribute("totalAnswerDuration", totalAnswerDuration);
        return "assistant/question-upload";
    }

    @GetMapping("/searchQuestionsByYear")
    @ResponseBody
    public List<Question> searchQuestionByYear(@RequestParam(required = false, value = "year") int year) {
        return questionService.getQuestionsByYear(year);
    }

    private Question populateFormData(Question form, MultipartFile file) {
        Question question;
        if (form.getId() != null) {
            question = questionService.getQuestionById(form.getId());
        } else {
            question = new Question();
        }

        if (!file.isEmpty()) {
            String fileName = questionService.generateQuestionName(form, FilenameUtils.getExtension(file.getOriginalFilename()));
            question.setFileName(fileName);
        }

        question.setAnswer(form.getAnswer());
        question.setSession(form.getSession());
        question.setYear(form.getSession().getYear());
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

    @GetMapping("/getSubtopic")
    @ResponseBody
    public List<SubTopic> getSubtopicByTopicId(@RequestParam(value = "topicId") long topicId){
        List<SubTopic> subTopics = subTopicService.getSubTopicsByTopicId(topicId);
        return subTopics;
    }
}
