package com.kids.crm.config;

import com.kids.crm.model.User;
import com.kids.crm.model.mongo.UserLoginSession;
import com.kids.crm.mongo.repository.UserLoginSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

public class AfterLoginSuccess implements AuthenticationSuccessHandler{
    @Autowired
    UserLoginSessionRepository userLoginSessionRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User authUser = (User) authentication.getPrincipal();
        UserLoginSession userLoginSession = UserLoginSession.builder()
                .email(authUser.getUsername())
                .name(authUser.getName())
                .ip(request.getRemoteHost())
                .refUserId(authUser.getId())
                .login(new Date())
                .role(authUser.getRole().getAuthority())
                .build();
        userLoginSessionRepository.save(userLoginSession);
        String targetUrl = determineTargetUrl(authentication);
        response.sendRedirect(targetUrl);
    }

    public String determineTargetUrl(Authentication authentication) {
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (authorities.contains("ROLE_STUDENT")) {
             return "/student/switch-batch";
        } else if (authorities.contains("ROLE_TEACHER")) {
            return "/teacher/switch-batch";
        } else if (authorities.contains("ROLE_ASSISTANT")) {
            return "/assistant/dashboard";
        } else if (authorities.contains("ROLE_SUPER_ADMIN")) {
            return "/superadmin/dashboard";
        } else {
            throw new IllegalStateException();
        }
    }
}
