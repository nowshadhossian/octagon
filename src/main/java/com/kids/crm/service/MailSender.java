package com.kids.crm.service;

import com.kids.crm.config.Config;
import com.kids.crm.model.*;
import com.kids.crm.repository.GuardianRepository;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.StudentRepository;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.utils.DateUtils;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Component("HappyMail")
@Log
public class MailSender {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private StudentAnswerRepository studentAnswerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GuardianRepository guardianRepository;
    @Autowired
    private VerifyEmailService verifyEmailService;
    @Autowired
    private Config config;

    @Value("${mail.from.email}")
    private String from;

    private void send(String subject, String body, String to, String... cc) {
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
        String content = "";
        try {
            content = new String(new ClassPathResource("email-templates/".concat(templateId)).getInputStream().readAllBytes());
        } catch (IOException e) {
            throw new IOException("Could not read template with ID = " + templateId, e);
        }
        return content;
    }

    private String getTemplate(Map<String, String> replacements, String templateName) {
        String cTemplate = null;
        try {
            cTemplate = loadTemplate(templateName);
        } catch (IOException e) {
            log.warning(e.getMessage());
        }

        if (cTemplate != null) {
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
                cTemplate = cTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }
        }

        return cTemplate;
    }

    public void sendEmailToParentsWithDailyExamResult() {
        String mailSubject = "[Octagon] Daily Exam Result";

        Date yesterdayStart = DateUtils.toDate(LocalDate.now().minusDays(1));
        Date yesterdayEnd = DateUtils.toDate(LocalDate.now());

        List<Student> students = studentRepository.findAll();


        //TODO need testing
        for (Student student : students) {
            Optional<Guardian> guardianOptional = guardianRepository.findByStudent(student).stream()
                    .filter(guardian -> StringUtils.isNotBlank(guardian.getEmail()))
                    .findFirst();
            if (guardianOptional == null) {
                continue;
            }
            List<Batch> batches = student.getBatches();
            for (Batch batch : batches) {

                List<StudentAnswer> studentAnswers = studentAnswerRepository.findByUserAndAttendedOnBetweenAndBatchAndExamType(student, yesterdayStart, yesterdayEnd, batch, ExamType.DAILY_EXAM);
                if (studentAnswers.isEmpty()) {
                   /* Map<String, String> params = new HashMap<>();
                    params.put("name", student.getName());

                    String body = getTemplate(params, "DailyExamResultToParentsDidNotAppear.html");
                    send(mailSubject, body, "parents@parent.com");*/
                } else {
                    long correct = studentAnswers.stream().filter(StudentAnswer::isGotCorrect).count();
                    long wrong = studentAnswers.stream().filter(answer -> !answer.isGotCorrect()).count();
                    Map<String, String> params = new HashMap<>();
                    params.put("name", student.getName());
                    params.put("correct", "" + correct);
                    params.put("wrong", "" + wrong);
                    params.put("skipped", "" + (10 - correct - wrong));


                    String body = getTemplate(params, "DailyExamResultToParents.html");
                    send(mailSubject, body, guardianOptional.get().getEmail());
                }
            }
        }
    }

    public void sendmailForResetPassword(String template,String resetUrl, User user){
        Map<String,String> params = new HashMap<>();
        String mailSubject = "Reset Password";
        params.put("name",user.getName());
        params.put("resetUrl",resetUrl);
        params.put("application","Octagon");
        String body = getTemplate(params,template);
        send(mailSubject,body,user.getEmail());

    }

    public void sendEmailToVerifyEmail(User user) {
        Map<String, String> params = new HashMap<>();
        String mailSubject = "Verify Email";
        params.put("name", user.getName());
        params.put("verifyUrl", verifyEmailService.generateVerifyEmailUrl(user));
        params.put("application", config.getCompanyName());
        String body = getTemplate(params, "VerifyEmailTemplate.html");
        send(mailSubject, body, user.getEmail());
    }
}
