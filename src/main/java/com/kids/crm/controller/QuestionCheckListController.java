package com.kids.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kids.crm.config.Config;
import com.kids.crm.model.ExamType;
import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.Topic;
import com.kids.crm.model.User;
import com.kids.crm.pojo.ExamSettingsDTO;
import com.kids.crm.service.Encryption;
import com.kids.crm.service.StudentService;
import com.kids.crm.service.TopicService;
import com.kids.crm.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class QuestionCheckListController {
    public static final String BASE_ROUTE = StudentDashboardController.BASE_ROUTE + "/question-checklist";

    @Autowired
    UserSession userSession;

    @Autowired
    StudentService studentService;

    @Autowired
    TopicService topicService;

    @Autowired private Config config;

    @Autowired private ObjectMapper objectMapper;

    @GetMapping(BASE_ROUTE)
    public String showQuestionCheckList(ModelMap modelMap, @RequestParam(value = "_topic", required = false) String topicParam) {
        User loggedInUser = userSession.getLoggedInUser();
        List<StudentAnswer> studentAnswers;
        if (topicParam == null || topicParam.equalsIgnoreCase("all")) {
            studentAnswers = studentService.getStudentAnswerByUserIdAndBatchId(loggedInUser.getId(), userSession.getCurrentBatch().getId());
        } else {
            studentAnswers = studentService.getStudentAnswerByUserIdAndBatchIdAndTopicId(loggedInUser.getId(), userSession.getCurrentBatch().getId(), Long.parseLong(topicParam));
        }
        modelMap.addAttribute("selectedFilter", topicParam);

        modelMap.addAttribute("studentAnswers", studentAnswers);

        List<Topic> topics = topicService.getAllTopic();
        modelMap.addAttribute("topics", topics);

        return "question-checklist";
    }

    @GetMapping(BASE_ROUTE + "/start/answer/questionNo/{questionNo}/year/{year}")
    public String startAnswering(ModelMap modelMap, @PathVariable int questionNo, @PathVariable int year) throws UnsupportedEncodingException, JsonProcessingException {
        User authUser = userSession.getLoggedInUser();
        modelMap.addAttribute("name", authUser.getName());
        String encryptedUserId = Encryption.encrypt(String.valueOf(authUser.getId()));
        ExamSettingsDTO examSettingsDTO = ExamSettingsDTO.builder()
                .examTypeId(ExamType.CUSTOM_EXAM.getId())
                .batchId(userSession.getCurrentBatch().getId())
                .totalQuestion(1)
                .questionNo(questionNo)
                .year(year)
                .showAnswersInTheEnd(false)
                .build();

        String examSettingsDtoEncrypted = Encryption.encrypt(objectMapper.writeValueAsString(examSettingsDTO));

        return "redirect:" + config.getExamUiDomain() + "/?u=" + URLEncoder.encode(encryptedUserId, "UTF-8")
                + "&s=" + URLEncoder.encode(examSettingsDtoEncrypted, "UTF-8");
    }


}
