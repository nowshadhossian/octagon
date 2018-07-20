package com.kids.crm.controller;

import com.kids.crm.service.Encryption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ForgotPasswordController {

    @GetMapping("/forgot-password1")
    public String forgotPassword(){
        return "forgot-password";
    }
    @PostMapping("/forgot-password1")
    public String submitRequest(@ModelAttribute String email, RedirectAttributes redirectAttributes){
        String encrypted = Encryption.encrypt(email);
        String decrypted = Encryption.decrypt(encrypted);
        if(email.equals(decrypted))
            System.out.println("success");
        redirectAttributes.addAttribute("message","success");
        return "redirect:/forgot-password1";
    }
}
