package com.kids.crm.config;

import com.kids.crm.model.Role;
import com.kids.crm.model.User;
import com.kids.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup){
            return;
        }

        if (userRepository.findByRole(Role.SUPER_ADMIN).size() == 0){
            User user = new User();
            user.setFirstName("Super");
            user.setLastName("Admin");
            user.setPassword(passwordEncoder.encode("cowboy2-1#IER82F@f"));
            user.setEmail("super@super.com");
            user.setEnabled(true);
            user.setRole(Role.SUPER_ADMIN);
            userRepository.save(user);
        }


        alreadySetup = true;
    }
}
