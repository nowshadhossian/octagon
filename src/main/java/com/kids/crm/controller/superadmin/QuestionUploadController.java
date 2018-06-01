package com.kids.crm.controller.superadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionUploadController {
    @GetMapping("/superadmin/question-upload")
    public String getSuperAdminPage() {
        return "super/question-upload";
    }
}
