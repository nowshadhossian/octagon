package repository;

import com.kids.crm.OctagonApplication;
import com.kids.crm.model.Question;
import com.kids.crm.repository.QuestionRepository;
import org.junit.Assert;
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
public class QuestionRepositoryTest {

    @Autowired QuestionRepository questionRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void findQuestionByYear_returnsQuestion(){
       // Question saved = entityManager.persistFlushFind(Question.builder().id(500l).year(2016).build());
        Question saved = questionRepository.saveAndFlush(Question.builder().year(2016).fileName("firstFile.png").build());

        Assert.assertEquals(questionRepository.findQuestionsByYear(2016).get(0).getFileName(), "firstFile.png");
    }

}
