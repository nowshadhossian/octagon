package com.kids.crm.service;

import com.kids.crm.model.Batch;
import com.kids.crm.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
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
}
