package com.kids.crm.repository;

import com.kids.crm.model.Batch;
import com.kids.crm.model.Student;
import com.kids.crm.model.StudentBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentBatchRepository extends JpaRepository<StudentBatch, Long> {
    List<StudentBatch> findByStudentAndBatch(Student student, Batch batch);
    List<StudentBatch> findAllByStudent(Student student);

}
