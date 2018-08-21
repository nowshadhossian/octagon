package com.kids.crm.controller.superadmin;

import com.kids.crm.config.Config;
import com.kids.crm.exportdata.ExportQuestionData;
import com.kids.crm.model.*;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.repository.SubjectRepository;
import com.kids.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SuperAdminDashboardController {

    @Autowired
    ExportQuestionData exportQuestionData;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    Config config;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/superadmin/dashboard")
    public String getSuperAdminPage() {
        return "super/dashboard";
    }

    @GetMapping("/superadmin/question/upload")
    public String startFileUpload() {
        final Subject subject = exportQuestionData.findOrCreateSubject("Physics");

        List<Question> questionList = exportQuestionData.readQuestionExcel(subject);
        questionList.
                forEach(question1 -> {
                    question1.setSubject(subject);
                    questionRepository.save(question1);
                });
        return "super/dashboard";
    }

    @GetMapping("/superadmin/danger-zone")
    public String dangerZonePage() {

        return "super/danger-zone";
    }

    @PostMapping(value = "/superadmin/danger-zone", params = "medical-db-init")
    public String initMedicalDb() {

        if (subjectRepository.findByName("MBBS admission exams") == null) {
            Subject subject = Subject.builder()
                    .name("MBBS admission exams")
                    .code("3333")
                    .build();
            subjectRepository.save(subject);
        }

        if(!userRepository.findByEmail("us@medeprepbd.com").isPresent()){
            User theCompany = Teacher.builder()
                    .address("Uttara")
                    .degree("BS Medical")
                    .phone("0181811111")
                    .build();
            theCompany.setFirstName("None");
            theCompany.setPassword(passwordEncoder.encode("Tha3UIadf$h@$g5%f1"));
            theCompany.setRole(Role.TEACHER);
            theCompany.setEmail("us@medeprepbd.com");
            theCompany.setEnabled(true);
            theCompany.setLastName("");

            userRepository.save(theCompany);
        }

        return "super/danger-zone";
    }
}
