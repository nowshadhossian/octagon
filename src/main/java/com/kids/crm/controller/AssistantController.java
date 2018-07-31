package com.kids.crm.controller;

import com.kids.crm.model.Assistant;
import com.kids.crm.model.Role;
import com.kids.crm.model.User;
import com.kids.crm.repository.AssistantRepository;
import com.kids.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/superadmin/assistant")
public class AssistantController {
    private final AssistantRepository assistantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AssistantController(AssistantRepository assistantRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.assistantRepository = assistantRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/list")
    public String list(ModelMap modelMap) {
        modelMap.addAttribute("assistants", assistantRepository.findAll());
        return "super/list-assistant";
    }

    @GetMapping("/add")
    public String addPage(ModelMap modelMap) {
        modelMap.addAttribute("assistant", new Assistant());
        return "super/add-assistant";
    }


    @PostMapping("/save")
    public String save(ModelMap modelMap, Assistant form) {
        User assistant = Assistant.builder()
                .address(form.getAddress())
                .phone(form.getPhone())
                .build();
        assistant.setFirstName(form.getFirstName());
        assistant.setPassword(passwordEncoder.encode(form.getPassword()));
        assistant.setRole(Role.ASSISTANT);
        assistant.setEmail(form.getEmail());
        assistant.setLastName(form.getLastName());
        assistant.setEnabled(true);

        userRepository.save(assistant);

        return "redirect:/superadmin/assistant/list";
    }
}
