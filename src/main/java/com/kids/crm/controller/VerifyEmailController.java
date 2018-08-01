package com.kids.crm.controller;

import com.kids.crm.model.User;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.service.MailSender;
import com.kids.crm.service.UserService;
import com.kids.crm.service.VerifyEmailService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/verify/email")
public class VerifyEmailController {

    private final VerifyEmailService verifyEmailService;
    private final UserRepository userRepository;
    private final MailSender mailSender;
    private final UserService userService;

    @Autowired
    public VerifyEmailController(VerifyEmailService verifyEmailService, UserRepository userRepository, MailSender mailSender, UserService userService) {
        this.verifyEmailService = verifyEmailService;
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.userService = userService;
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

    @GetMapping("/resend")
    public String resendVerifyEmail() {
        return "resend-verify-email";
    }

    @PostMapping("/resend")
    public String sendResendVerifyEmail(String email, RedirectAttributes redirectAttributes) {
        if (EmailValidator.getInstance().isValid(email)) {
            mailSender.sendEmailToVerifyEmail((User) userService.loadUserByUsername(email));
            redirectAttributes.addFlashAttribute("successMsg", "Verify email sent!!");
        } else {
            redirectAttributes.addFlashAttribute("errorMsg", "Invalid Email");
        }

        return "redirect:/login";
    }
}
