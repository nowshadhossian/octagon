package service;

import com.kids.crm.model.Question;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.service.GraphService;
import com.kids.crm.service.QuestionService;
import com.kids.crm.service.UserService;
import com.kids.crm.service.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    @Autowired
    GraphService graphService;
    @Autowired
    UserService userService;

    private QuestionService questionService;
    @Mock
    QuestionRepository questionRepository;

    @Before
    public void setUp(){
        questionService = new QuestionService(questionRepository);
    }

    @Test
    public void getQuestionById_returnsQuestion(){
        given(questionRepository.findById(2l)).willReturn(Optional.of(
                Question.builder().id(2l).fileName("abcd").build()));

        Assert.assertEquals(questionService.getQuestionById(2l).getFileName(), "abcd");
    }

    @Test(expected = NotFoundException.class)
    public void getQuestionById_whenQuestionNotExist(){
        given(questionRepository.findById(4l)).willReturn(Optional.empty());

        questionService.getQuestionById(4l);

    }

//    @Test
//    public void averageQuestionSolvingTime(){
////        graphService.averageTimePerQues(userService.getAuthenticateUser(3l));
//        System.out.println(userService.getAuthenticateUser(3L));
//    }

}
