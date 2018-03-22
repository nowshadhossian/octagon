package com.kids.crm.controller;

import com.kids.crm.config.Config;
import com.kids.crm.model.User;
import com.kids.crm.service.Encryption;
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

    @Autowired
    public DailyExamController(Config config) {
        this.config = config;
    }


    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
    private String dailyExam(Authentication authentication, ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        User authUser = (User) authentication.getPrincipal();
        modelMap.addAttribute("name", authUser.getName());
     //   response.addHeader("userId", "" + authUser.getId());
        String encryptedUserId = Encryption.encrypt(String.valueOf(authUser.getId()));
        return "redirect:" + config.getExamUiDomain() + "/?u=" + URLEncoder.encode(encryptedUserId, "UTF-8");
    }
}
