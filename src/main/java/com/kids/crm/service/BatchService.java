package com.kids.crm.service;

import com.kids.crm.model.Batch;
import com.kids.crm.repository.BatchRepository;
import com.kids.crm.service.exception.BatchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BatchService {
    private BatchRepository batchRepository;

    @Autowired
    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    public List<Batch> getUpcomingBatches() {
        LocalDate localDate = LocalDate.now();
        return batchRepository.findAll().stream()
                .filter(batch -> batch.getSession().getYear() >= localDate.getYear())
                .collect(Collectors.toList());
    }

    public void save(Batch batch){
        batchRepository.save(batch);
    }

    public Batch findBySessionIdAndSubjecId(long sessionId, long subjectId){
        return batchRepository.findBySubjectIdAndSessionId(subjectId, sessionId);
    }

    public Batch reFetch(Batch batch){
        return batchRepository.findById(batch.getId()).orElseThrow(BatchNotFoundException::new);
    }

    public Batch findById(long batchId){
        return batchRepository.findById(batchId).orElseThrow(BatchNotFoundException::new);
    }

    public Optional<Batch> findBySessionIdAndSubjectIdAndTeacherId(long sessionId, long subjectId, long teacherId) {
        return batchRepository.findBySubjectIdAndSessionIdAndTeacherId(subjectId, sessionId, teacherId);
    }
}
