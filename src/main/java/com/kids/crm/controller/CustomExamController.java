package com.kids.crm.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kids.crm.config.Config;
import com.kids.crm.model.ExamType;
import com.kids.crm.model.User;
import com.kids.crm.pojo.ExamSettingsDTO;
import com.kids.crm.repository.TopicRepository;
import com.kids.crm.service.Encryption;
import com.kids.crm.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomExamController {
    private final UserSession userSession;
    private final ObjectMapper objectMapper;
    private final TopicRepository topicRepository;
    private final Config config;

    @Autowired
    public CustomExamController(UserSession userSession, ObjectMapper objectMapper, TopicRepository topicRepository, Config config) {
        this.userSession = userSession;
        this.objectMapper = objectMapper;
        this.topicRepository = topicRepository;
        this.config = config;
    }

    @RequestMapping(value = "/custom/exam", method = RequestMethod.GET)
    private String customExamSettings(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException, JsonProcessingException {
        User authUser = userSession.getLoggedInUser();
        modelMap.addAttribute("name", authUser.getName());
        modelMap.addAttribute("topics", topicRepository.findBySubject(userSession.getCurrentBatch().getSubject()));

        List<Integer> years = new ArrayList<>();
        for(int i = LocalDate.now().getYear(); i > 2000; i--){
            years.add(i);
        }
        modelMap.addAttribute("years", years);

        return "/exam/custom-exam-settings";
    }

    @RequestMapping(value = "/custom/exam", method = RequestMethod.POST)
    private String customExamSettingsSaveAndStart(ModelMap modelMap, @RequestParam(required = false) long topicId, @RequestParam(required = false, defaultValue = "10") int questionCount,
                                                  @RequestParam(required = false, defaultValue = "-1") int year) throws JsonProcessingException, UnsupportedEncodingException {
        User authUser = userSession.getLoggedInUser();
        modelMap.addAttribute("name", authUser.getName());
        String encryptedUserId = Encryption.encrypt(String.valueOf(authUser.getId()));
        ExamSettingsDTO examSettingsDTO = ExamSettingsDTO.builder()
                .examTypeId(ExamType.CUSTOM_EXAM.getId())
                .batchId(userSession.getCurrentBatch().getId())
                .totalQuestion(questionCount)
                .topicId(topicId)
                .year(year)
                .build();

        String examSettingsDtoEncrypted = Encryption.encrypt(objectMapper.writeValueAsString(examSettingsDTO));

        return "redirect:" + config.getExamUiDomain() + "/?u=" + URLEncoder.encode(encryptedUserId, "UTF-8")
                + "&s=" + URLEncoder.encode(examSettingsDtoEncrypted, "UTF-8");
    }
}
