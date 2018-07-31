package com.kids.crm.repository;

import com.kids.crm.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findBySubjectIdAndSessionId(long subjectId, long sessionId);

    Optional<Batch> findBySubjectIdAndSessionIdAndTeacherId(long subjectId, long sessionId, long teacherId);
}
