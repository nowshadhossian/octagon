package com.kids.crm.service;

import com.kids.crm.model.Session;
import com.kids.crm.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionService {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


    public Session findOrCreateSessionByNameAndYear(String name, int year){
        return sessionRepository.findByNameAndYear(name, year)
                 .orElseGet(() -> sessionRepository.save(Session.builder().name(name).year(year).build()));
    }
}
