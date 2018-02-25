package com.kids.crm.controller;

import com.kids.crm.model.AuthUser;
import com.kids.crm.service.Encryption;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class DailyExamController {
    private static final String BASE_ROUTE = "/daily/exam";

    @RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
    private String dailyExam(Authentication authentication, ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        modelMap.addAttribute("name", authUser.getName());
     //   response.addHeader("userId", "" + authUser.getId());
        String encryptedUserId = Encryption.encrypt(String.valueOf(authUser.getId()));
        return "redirect:http://localhost:3000/?u=" + URLEncoder.encode(encryptedUserId, "UTF-8");
    }
}
