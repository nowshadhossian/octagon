package com.kids.crm.repository;

import com.kids.crm.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String name);
}
