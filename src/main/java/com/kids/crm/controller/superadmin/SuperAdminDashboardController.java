package com.kids.crm.controller.superadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuperAdminDashboardController {

    @GetMapping("/superadmin/dashboard")
    public String getSuperAdminPage(){
        return "super/dashboard";
    }
}
