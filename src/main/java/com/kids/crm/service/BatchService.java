package com.kids.crm.service;

import com.kids.crm.model.Batch;
import com.kids.crm.repository.BatchRepository;
import com.kids.crm.service.exception.BatchNotFoundException;
import com.kids.crm.service.exception.NotFoundException;
import com.kids.crm.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchService {
    private final BatchRepository batchRepository;

    @Autowired private TeacherService teacherService;
    @Autowired private SessionService sessionService;
    @Autowired private SubjectService subjectService;

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

    public List<Batch> findBySessionIdAndSubjectId(long sessionId, long subjectId){
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

    public Batch findOrCreateBySessionIdAndSubjectIdAndTeacherId(long sessionId, long subjectId, long teacherId) {
        return findBySessionIdAndSubjectIdAndTeacherId(sessionId, subjectId, teacherId)
                .orElseGet(() -> createBatch(sessionId, subjectId, teacherId));
    }

    private Batch createBatch(long sessionId, long subjectId, long teacherId) {
        return batchRepository.save(Batch.builder()
                .teacher(teacherService.findById(teacherId).orElseThrow(new UserNotFoundException(teacherId)))
                .session(sessionService.getAllSessions().stream()
                        .filter(session -> Objects.equals(session.getId(), sessionId))
                        .findFirst()
                        .orElseThrow(NotFoundException::new)
                )
                .subject(subjectService.getSubjects().stream()
                        .filter(subject -> Objects.equals(subject.getId(), subjectId))
                        .findFirst()
                        .orElseThrow(NotFoundException::new)
                )
                .build());

    }
}
