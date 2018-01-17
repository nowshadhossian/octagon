package com.kids.crm.repository;

import com.kids.crm.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
     Student findByName(String name);

    List<Student> findAll();
}
