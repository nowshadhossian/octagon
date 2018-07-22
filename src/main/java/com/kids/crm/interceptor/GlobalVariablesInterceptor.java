package com.kids.crm.interceptor;

import com.kids.crm.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public class GlobalVariablesInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    UserSession userSession;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            Principal user = request.getUserPrincipal();
            modelAndView.addObject("LOGGED_IN_USER", userSession.getLoggedInUser());
        }
    }
}
