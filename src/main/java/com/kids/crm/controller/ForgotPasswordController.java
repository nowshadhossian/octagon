package com.kids.crm.controller;

import com.kids.crm.model.ForgotPassword;
import com.kids.crm.model.ResetPassword;
import com.kids.crm.model.User;
import com.kids.crm.service.ForgotPasswordService;
import com.kids.crm.service.UserService;
import com.kids.crm.validator.ResetPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;
    @Autowired
    private ResetPasswordValidator validator;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @GetMapping("/forgot-password1")
    public String forgotPassword(@RequestParam(value = "success",required = false) boolean success,ModelMap modelMap){

        return "forgot-password";
    }
    @PostMapping("/forgot-password1")
    public String submitRequest(@ModelAttribute ForgotPassword forgotPasswordBean, RedirectAttributes redirectAttributes){
        boolean isSend = forgotPasswordService.processForgetPassword(forgotPasswordBean.getEmail());
        redirectAttributes.addFlashAttribute("success",isSend);
        return "redirect:/forgot-password1";
    }
    @GetMapping("/reset-password")
    public String getResetPassword(@RequestParam("_token") String token,Model modelMap){
        User requestedUser = forgotPasswordService.processResetPassword(token);
        if(requestedUser==null){
            modelMap.addAttribute("requestMessage","Your request is invalid");
        } else {
            modelMap.addAttribute("user",requestedUser);
        }
        return "reset-password";
    }
    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute ResetPassword form, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        validator.validate(form,bindingResult);
        if(bindingResult.hasErrors()){
            return "reset-password";
        }
        User user = userService.loadUserById(form.getUserId());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("requestMessage","Password reset successfully done");
        return "redirect:/login";
    }

}
