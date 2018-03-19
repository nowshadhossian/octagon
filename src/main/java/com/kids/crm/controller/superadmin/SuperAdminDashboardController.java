package com.kids.crm.controller.superadmin;

import com.kids.crm.exportdata.ExportQuestionData;
import com.kids.crm.model.Question;
import com.kids.crm.model.Subject;
import com.kids.crm.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SuperAdminDashboardController {

    @Autowired
    ExportQuestionData exportQuestionData;
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/superadmin/dashboard")
    public String getSuperAdminPage(){
        return "super/dashboard";
    }

    @GetMapping("/superadmin/question/upload")
    public String startFileUpload(){
        final Subject subject = exportQuestionData.findOrCreateSubject("Physics");

        List<Question> questionList = exportQuestionData.readQuestionExcel(subject);
        questionList.
                forEach(question1 -> {
            question1.setSubject(subject);
            questionRepository.save(question1);
        });
        return "super/dashboard";
    }
}
