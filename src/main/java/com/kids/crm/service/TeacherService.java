package com.kids.crm.service;

import com.kids.crm.model.Student;
import com.kids.crm.model.StudentBatch;
import com.kids.crm.model.Teacher;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.StudentBatchRepository;
import com.kids.crm.repository.StudentRepository;
import com.kids.crm.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final StudentRepository studentRepository;
    private final StudentAnswerRepository studentAnswerRepository;
    private final SessionService sessionService;
    private final BatchService batchService;
    private final TeacherRepository teacherRepository;
    private final StudentBatchRepository studentBatchRepository;

    @Autowired
    public TeacherService(StudentRepository studentRepository, StudentAnswerRepository studentAnswerRepository, SessionService sessionService, BatchService batchService, TeacherRepository teacherRepository, StudentBatchRepository studentBatchRepository) {
        this.studentRepository = studentRepository;
        this.studentAnswerRepository = studentAnswerRepository;
        this.sessionService = sessionService;
        this.batchService = batchService;
        this.teacherRepository = teacherRepository;
        this.studentBatchRepository = studentBatchRepository;
    }

    @Transactional
    public void removeStudentFromBatch(long studentId, long batchId) {
        studentAnswerRepository.removeByUserIdAndBatchId(studentId, batchId);

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            Optional<StudentBatch> studentBatchOptional = studentBatchRepository.findByStudentAndBatch(student, batchService.findById(batchId)).stream().findFirst();
            studentBatchOptional.ifPresent(studentBatchRepository::delete);
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

    public List<Teacher> findAll(){
        return teacherRepository.findAll();
    }
}
