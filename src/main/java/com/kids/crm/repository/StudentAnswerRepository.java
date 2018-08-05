package com.kids.crm.repository;

import com.kids.crm.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long>{

    List<StudentAnswer> findByUserAndAttendedOnBetweenAndExamType(User user, Date from, Date to, ExamType dailyExam);
    List<StudentAnswer> findByUserAndAttendedOnBetweenAndBatchAndExamType(User user, Date from, Date to, Batch batch, ExamType dailyExam);
    List<StudentAnswer> findByUser(User user);

    List<StudentAnswer> findStudentAnswerByUserIdAndBatchId(long userId,long batchId);
    List<StudentAnswer> findStudentAnswerByUserIdAndBatchIdAndQuestionTopicId(long userId, long batchId, long topicId);
    List<StudentAnswer> findByUserAndAttendedOnBetween(User user, Date from, Date to);
    List<StudentAnswer> findByUserAndAttendedOnBetweenOrderByAttendedOn(User user, Date from, Date to);
    void removeByUserIdAndBatchId(long userId, long batchId);

    Optional<StudentAnswer> findByUserAndBatchAndQuestion(User user, Batch batch, Question question);
}
