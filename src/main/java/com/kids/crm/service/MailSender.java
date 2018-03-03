package com.kids.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Component("HappyMail")
public class MailSender {

    @Autowired private JavaMailSender mailSender;

    @Value("${mail.from.email}")
    private String from;



    public void send(String subject, String body, String to, String... cc) {
        try {
            boolean isHtml = true;
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(to);
            helper.setReplyTo(from);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(body, isHtml);
            if (cc.length > 0) {
                helper.setCc(cc);
            }
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String loadTemplate(String templateId) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("email-templates/" + templateId).getFile());
        String content = "";
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new IOException("Could not read template with ID = " + templateId);
        }
        return content;
    }

    private String getTemplate(Map<String, String> replacements, String templateName) {
        String cTemplate = null;
        try {
            cTemplate = loadTemplate(templateName);
        } catch (IOException e) {
        }

        if (cTemplate != null) {
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
                cTemplate = cTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }
        }

        return cTemplate;
    }

    public void sendEmailToParentsWithDailyExamResult(){
        String subject = "[Octagon] Daily Exam Result";

        Map<String, String> params = new HashMap<>();
        params.put("name", "Jashim");
        params.put("correct", "6");
        params.put("wrong", "2");
        params.put("skipped", "3");


        String body = getTemplate(params, "DailyExamResultToParents.html");
        send(subject, body, "parents@parent.com");
    }
}
