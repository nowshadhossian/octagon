package com.kids.crm.repository;

import com.kids.crm.model.Batch;
import com.kids.crm.model.ExamType;
import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long>{

    List<StudentAnswer> findByUserAndAttendedOnBetweenAndExamType(User user, Date from, Date to, ExamType dailyExam);
    List<StudentAnswer> findByUserAndAttendedOnBetweenAndBatchAndExamType(User user, Date from, Date to, Batch batch, ExamType dailyExam);
    List<StudentAnswer> findByUser(User user);

    List<StudentAnswer> findStudentAnswerByUserIdAndBatchId(long userId,long batchId);
    List<StudentAnswer> findStudentAnswerByUserIdAndBatchIdAndQuestionTopicId(long userId, long batchId, long topicId);
    List<StudentAnswer> findByUserAndAttendedOnBetween(User user, Date from, Date to);
    void removeByUserIdAndBatchId(long userId, long batchId);
}
