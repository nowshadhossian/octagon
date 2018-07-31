package com.kids.crm.service;

import com.kids.crm.model.User;
import com.kids.crm.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static com.kids.crm.utils.Constants.KEY_TIMESTAMP;

@Service
public class VerifyEmailService {

    private final UserService userService;
    @Value("${app.root.url}")
    private String rootUrl;

    @Autowired
    public VerifyEmailService(UserService userService) {
        this.userService = userService;
    }

    public User processVerifyEmail(String token) {
        return getValidRequestedUser(token);
    }

    private User getValidRequestedUser(String token) {
        String decryptedToken = "";
        try {
            decryptedToken = Encryption.decrypt(token);
            List<String> elements = Arrays.asList(decryptedToken.split(";"));
            String userId = getParameterValue(elements, Constants.KEY_ID);
            String timeStamp = getParameterValue(elements, Constants.KEY_TIMESTAMP);
            User user = userService.loadUserById(Long.parseLong(userId));
            if (user == null) {
                return null;
            }
            Instant instant = Instant.now();
            long currentTimeStampSeconds = instant.getEpochSecond();
            long timeStampSeconds = Long.parseLong(timeStamp);
            if (timeStampSeconds <= currentTimeStampSeconds) {
                return user;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private String getParameterValue(List<String> elements, String key) {
        String value = "";
        for (String element : elements) {
            List<String> list = Arrays.asList(element.split(":"));
            if (list.size() == 2 && list.get(0).equals(key)) {
                value = list.get(1);
                break;
            }
        }
        return value;
    }

    public String generateVerifyEmailUrl(User user) {
        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();
        String token = Constants.KEY_ID + ":" + user.getId() + ";" + KEY_TIMESTAMP + ":" + timeStampSeconds;
        String encToken = Encryption.encrypt(token);
        String resetUrl = null;
        try {
            resetUrl = rootUrl + "/verify/email/student?_token=" + URLEncoder.encode(encToken, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resetUrl;
    }
}
