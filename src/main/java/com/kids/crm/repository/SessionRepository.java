package com.kids.crm.repository;

import com.kids.crm.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Session findByNameAndYear(String name, int year);
}
