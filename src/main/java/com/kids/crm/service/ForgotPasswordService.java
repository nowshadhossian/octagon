package com.kids.crm.service;

import com.kids.crm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
public class ForgotPasswordService {
    @Autowired
    UserService userService;

    @Autowired
    MailSender mailSender;

    @Value("${app.root.url}")
    private String rootUrl;

    private final long TOKEN_DURATION = 24*60*60;
    private final String KEY_ID = "id";
    private final String KEY_TIMESTAMP = "timestamp";
    private final String KEY_EMAIL = "email";


    public boolean processForgetPassword(String email){
        User user = (User) userService.loadUserByUsername(email);
        String resetUrl = generateResetPasswordURL(user);
        mailSender.sendmailForResetPassword("forgotPasswordMailTemplate.html",resetUrl,user);
        return true;
    }

    public User processResetPassword(String token) {
        User requestedUser = getValidRequestedUser(token);
        return requestedUser;
    }

    private String generateResetPasswordURL(User user){
        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();
        String token =KEY_ID+":"+user.getId()+";"+KEY_TIMESTAMP+":"+timeStampSeconds;
        String encToken = Encryption.encrypt(token);
        String resetUrl = null;
        try {
            resetUrl = rootUrl+"/reset-password?_token="+URLEncoder.encode(encToken,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resetUrl;
    }

    private User getValidRequestedUser(String token){
        String decryptedToken = "";
        try {
            decryptedToken = Encryption.decrypt(token);
            List<String> elements = Arrays.asList(decryptedToken.split(";"));
            String userId = getParameterValue(elements,KEY_ID);
            String timeStamp = getParameterValue(elements,KEY_TIMESTAMP);
            User user = userService.loadUserById(Long.parseLong(userId));
            if (user==null){
                return user;
            }
            Instant instant = Instant.now();
            long currentTimeStampSeconds = instant.getEpochSecond();
            long timeStampSeconds = Long.parseLong(timeStamp);
            if(timeStampSeconds<=currentTimeStampSeconds){
                return user;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private String getParameterValue(List<String> elements,String key){
        String value="";
        for (String element: elements) {
            List<String> list = Arrays.asList(element.split(":"));
            if (list.size()==2 && list.get(0).equals(key)){
                value = list.get(1);
                break;
            }
        }
        return value;
    }

}
