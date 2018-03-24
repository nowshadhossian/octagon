package com.kids.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kids.crm.config.Config;
import com.kids.crm.model.ExamType;
import com.kids.crm.model.User;
import com.kids.crm.pojo.ExamSettingsDTO;
import com.kids.crm.service.Encryption;
import com.kids.crm.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class DailyExamController {
    private static final String BASE_ROUTE = "/daily/exam";

    private final Config config;
    private final UserSession userSession;
    private final ObjectMapper objectMapper;

    @Autowired
    public DailyExamController(Config config, UserSession userSession, ObjectMapper objectMapper) {
        this.config = config;
        this.userSession = userSession;
        this.objectMapper = objectMapper;
    }


    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
    private String dailyExam(Authentication authentication, ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException, JsonProcessingException {
        User authUser = (User) authentication.getPrincipal();
        modelMap.addAttribute("name", authUser.getName());
     //   response.addHeader("userId", "" + authUser.getId());
        String encryptedUserId = Encryption.encrypt(String.valueOf(authUser.getId()));
        ExamSettingsDTO examSettingsDTO = ExamSettingsDTO.builder()
                                            .examTypeId(ExamType.DAILY_EXAM.getId())
                                            .batchId(userSession.getCurrentBatch().getId())
                                            .totalQuestion(10)
                                            .build();

        String examSettingsDtoEncrypted = Encryption.encrypt(objectMapper.writeValueAsString(examSettingsDTO));

        return "redirect:" + config.getExamUiDomain() + "/?u=" + URLEncoder.encode(encryptedUserId, "UTF-8")
                + "&s=" + URLEncoder.encode(examSettingsDtoEncrypted, "UTF-8");
    }
}
