package com.kids.crm.repository;

import com.kids.crm.model.ExamType;
import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long>{

    List<StudentAnswer> findByUserAndAttendedOnBetweenAndExamType(User user, Date from, Date to, ExamType dailyExam);
}
