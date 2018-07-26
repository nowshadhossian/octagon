package repository;

import com.kids.crm.OctagonApplication;
import com.kids.crm.model.Batch;
import com.kids.crm.model.Student;
import com.kids.crm.model.StudentBatch;
import com.kids.crm.repository.StudentBatchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = OctagonApplication.class)
@DataJpaTest
public class StudentBatchRepositoryTest {
    @Autowired
    StudentBatchRepository studentBatchRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void findStudentBatchByStudentAndBatch_returnsQuestion(){
        StudentBatch saved = studentBatchRepository.saveAndFlush(StudentBatch.builder()
                .student(Student.builder().build())
                .batch(Batch.builder().build())
                .build());
    }
}
