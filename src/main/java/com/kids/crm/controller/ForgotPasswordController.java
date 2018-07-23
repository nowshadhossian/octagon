package com.kids.crm.controller;

import com.kids.crm.pojo.ForgotPasswordBean;
import com.kids.crm.service.Encryption;
import com.kids.crm.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;

@Controller
public class ForgotPasswordController {

    @Autowired
    ForgotPasswordService forgotPasswordService;

    @GetMapping("/forgot-password1")
    public String forgotPassword(@RequestParam(value = "success",required = false) boolean success,ModelMap modelMap){

        return "forgot-password";
    }
    @PostMapping("/forgot-password1")
    public String submitRequest(@ModelAttribute ForgotPasswordBean forgotPasswordBean, RedirectAttributes redirectAttributes){
        boolean isSend = forgotPasswordService.processForgetPassword(forgotPasswordBean.getEmail());
        redirectAttributes.addFlashAttribute("success",isSend);
        return "redirect:/forgot-password1";
    }
    @GetMapping("/reset-password")
    public String getResetPassword(@RequestParam("_token") String token,Model modelMap){
        boolean isTokenValidate = forgotPasswordService.processResetPassword(token);
        if(!isTokenValidate){
            modelMap.addAttribute("requestMessage","Your request is invalid");
        }
        return "reset-password";
    }
    @PostMapping("/reset-password")
    public String resetPassword(){

        return "";
    }

}
