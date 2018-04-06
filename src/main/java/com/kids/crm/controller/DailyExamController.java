package com.kids.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kids.crm.config.Config;
import com.kids.crm.model.ExamType;
import com.kids.crm.model.User;
import com.kids.crm.pojo.ExamSettingsDTO;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.service.Encryption;
import com.kids.crm.service.UserSession;
import com.kids.crm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class DailyExamController {
    private static final String BASE_ROUTE = "/daily/exam";

    private final Config config;
    private final UserSession userSession;
    private final ObjectMapper objectMapper;
    private final StudentAnswerRepository studentAnswerRepository;

    @Autowired
    public DailyExamController(Config config, UserSession userSession, ObjectMapper objectMapper, StudentAnswerRepository studentAnswerRepository) {
        this.config = config;
        this.userSession = userSession;
        this.objectMapper = objectMapper;
        this.studentAnswerRepository = studentAnswerRepository;
    }


    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
    private String dailyExam(Authentication authentication, ModelMap modelMap, HttpServletResponse response,
                             HttpServletRequest request, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, JsonProcessingException {
        User authUser = (User) authentication.getPrincipal();
        modelMap.addAttribute("name", authUser.getName());

        Date todayStart = DateUtils.toDate(LocalDate.now().minusDays(0));
        Date todayEnd = DateUtils.toDate(LocalDate.now().minusDays(-1));

        if(studentAnswerRepository.findByUserAndAttendedOnBetweenAndBatchAndExamType(authUser, todayStart, todayEnd, userSession.getCurrentBatch(), ExamType.DAILY_EXAM).size() >= 1){
            redirectAttributes.addFlashAttribute("errorMsg", "You have attended the exam already. Try again tomorrow.");
            return "redirect:/student/dashboard";
        }

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
