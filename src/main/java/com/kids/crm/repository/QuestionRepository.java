package com.kids.crm.repository;

import com.kids.crm.model.IdOnly;
import com.kids.crm.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuerydslPredicateExecutor<Question> {
    int countBySubjectId(long subjectId);
  //  List<Question> findBySubjectId(long subjectId);
    List<IdOnly> findBySubjectId(long subjectId);
    Page<Question> findBySubjectId(long subjectId, Pageable pageable);
    List<Question> findByIdIn(Set<Long> ids);
    List<Question> findQuestionsByYear(int year);
}
