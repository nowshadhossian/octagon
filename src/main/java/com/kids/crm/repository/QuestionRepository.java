package com.kids.crm.repository;

import com.kids.crm.model.Question;
import com.kids.crm.model.Subject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long>{
    int countBySubjectId(long subjectId);
    List<Question> findBySubjectId(long subjectId);
}
