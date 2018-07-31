package com.kids.crm.controller;

import com.kids.crm.model.User;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.service.VerifyEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/verify/email")
public class VerifyEmailController {

    private final VerifyEmailService verifyEmailService;
    private final UserRepository userRepository;

    @Autowired
    public VerifyEmailController(VerifyEmailService verifyEmailService, UserRepository userRepository) {
        this.verifyEmailService = verifyEmailService;
        this.userRepository = userRepository;
    }


    @GetMapping("/student")
    public String processVerifyEmail(@RequestParam("_token") String token, Model modelMap, RedirectAttributes redirectAttributes) {
        User requestedUser = verifyEmailService.processVerifyEmail(token);
        if (requestedUser == null) {
            redirectAttributes.addFlashAttribute("requestMessage", "Your request is invalid");
        } else {
            requestedUser.setEnabled(true);
            userRepository.save(requestedUser);

            redirectAttributes.addFlashAttribute("successMsg", "Your Email has been verified.");
        }
        return "redirect:/login";
    }
}
