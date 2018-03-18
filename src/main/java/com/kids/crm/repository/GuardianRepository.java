package com.kids.crm.repository;

import com.kids.crm.model.Guardian;
import com.kids.crm.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {
    List<Guardian> findByStudent(Student student);
}
