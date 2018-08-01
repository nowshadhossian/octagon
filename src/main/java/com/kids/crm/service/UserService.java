package com.kids.crm.service;

import com.kids.crm.model.ExamType;
import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.User;
import com.kids.crm.pojo.LastAttendedResult;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

@Component
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private StudentAnswerRepository studentAnswerRepository;
    private UserSession userSession;

    @Autowired
    public UserService(UserRepository userRepository, StudentAnswerRepository studentAnswerRepository, UserSession userSession) {
        this.userRepository = userRepository;
        this.studentAnswerRepository = studentAnswerRepository;
        this.userSession = userSession;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " username Not Found"));
    }

    public User loadUserById(long id){
        return userRepository.findById(id).get();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

}
