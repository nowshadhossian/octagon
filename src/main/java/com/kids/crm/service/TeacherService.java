package com.kids.crm.service;

import com.kids.crm.model.Student;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TeacherService {
    private final StudentRepository studentRepository;
    private final StudentAnswerRepository studentAnswerRepository;
    private final SessionService sessionService;
    private final BatchService batchService;

    @Autowired
    public TeacherService(StudentRepository studentRepository, StudentAnswerRepository studentAnswerRepository, SessionService sessionService, BatchService batchService) {
        this.studentRepository = studentRepository;
        this.studentAnswerRepository = studentAnswerRepository;
        this.sessionService = sessionService;
        this.batchService = batchService;
    }

    @Transactional
    public void removeStudentFromBatch(long studentId, long batchId) {
        studentAnswerRepository.removeByUserIdAndBatchId(studentId, batchId);

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.removeFromBatch(batchService.findById(batchId));
            studentRepository.save(student);
        }
       /*Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent()){
            Student student1 = studentOptional.get();
            for(Batch batch1 : student1.getBatches()){
                if(batch1.getId() == batchId){
                    student1.getBatches().remove(batch1);
                    studentRepository.save(student1);
                    break;
                }
            }
        }*/
    }
}
