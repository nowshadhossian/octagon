package com.kids.crm.repository;

import com.kids.crm.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository  extends JpaRepository<Teacher, Long> {
}
