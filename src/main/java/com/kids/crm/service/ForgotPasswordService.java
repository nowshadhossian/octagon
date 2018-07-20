package com.kids.crm.service;

import com.kids.crm.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
public class ForgotPasswordService {
    @Autowired
    UserService userService;

    private final long TOKEN_DURATION = 24*60*60;
    private final String KEY_ID = "id";
    private final String KEY_TIMESTAMP = "timestamp";
    private final String KEY_EMAIL = "email";


    public String processForgetPassword(String email){
        User user = (User) userService.loadUserByUsername(email);
        String resetUrl = generateResetPasswordURL(user);

        return "";
    }

    public String processResetPassword(String token) {
        validateToken(token);
        return "";
    }

    private String generateResetPasswordURL(User user){
        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();
        String token = Encryption.encrypt(KEY_EMAIL+":"+user.getEmail()+";"+KEY_ID+":"+user.getId()+";"+KEY_TIMESTAMP+":"+timeStampSeconds);
        String resetUrl ="?_token="+token;
        return resetUrl;
    }

    private boolean validateToken(String token){
        String decryptedToken = Encryption.decrypt(token);
        List<String> elements = Arrays.asList(decryptedToken.split(";"));
        String userEmail = getParameterValue(elements,KEY_EMAIL);
        String timeStamp = getParameterValue(elements,KEY_TIMESTAMP);
        User user = (User) userService.loadUserByUsername(userEmail);
        if (user==null){
            return false;
        }
        Instant instant = Instant.now();
        long currentTimeStampSeconds = instant.getEpochSecond();
        long timeStampSeconds = Long.parseLong(timeStamp);
        if(timeStampSeconds<=currentTimeStampSeconds){
            return true;
        }
        return false;
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
