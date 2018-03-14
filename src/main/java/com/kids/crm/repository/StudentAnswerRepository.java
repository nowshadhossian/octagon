package com.kids.crm.repository;

import com.kids.crm.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long>{

    List<StudentAnswer> findByUserAndAttendedOnBetweenAndExamType(User user, Date from, Date to, ExamType dailyExam);
    List<StudentAnswer> findByUserAndAttendedOnBetweenAndBatchAndExamType(User user, Date from, Date to, Batch batch, ExamType dailyExam);
    List<StudentAnswer> findByUser(User user);
}
