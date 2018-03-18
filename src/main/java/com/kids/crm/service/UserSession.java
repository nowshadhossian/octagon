package com.kids.crm.service;

import com.kids.crm.model.Batch;
import com.kids.crm.model.Subject;
import com.kids.crm.model.User;
import com.kids.crm.repository.BatchRepository;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.repository.SubjectRepository;
import com.kids.crm.service.exception.BatchNotFoundException;
import com.kids.crm.service.exception.SubjectNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {
    private Subject currentSubject;
    private Batch currentBatch;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    BatchRepository batchRepository;

    public User getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public Subject getCurrentSubject() {
        return getCurrentBatch().getSubject();
    }

   /* public Batch getCurrentBatch(){
        return batchRepository.findById(1l).orElseThrow(BatchNotFoundException::new);
    }*/
}
