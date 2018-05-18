package com.kids.crm.repository;

import com.kids.crm.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Cacheable;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    @org.springframework.cache.annotation.Cacheable("findByPesel")
    Batch findBySubjectIdAndSessionId(long subjectId, long sessionId);
}
