package com.kids.crm.repository;

import com.kids.crm.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByNameAndYear(String name, int year);

}
