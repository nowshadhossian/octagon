package com.kids.crm.controller.api;

import com.kids.crm.controller.api.data.QuestionsData;
import com.kids.crm.model.Question;
import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.User;
import com.kids.crm.model.mongo.QuestionSolvingTime;
import com.kids.crm.mongo.repository.QuestionSolvingTimeRepository;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.service.Encryption;
import com.kids.crm.service.JwtToken;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class RestApiController {
    private static final String BASE_ROUTE = "/api";

    private final QuestionRepository questionRepository;
    private final DataMapper mapper;
    private final JwtToken jwtToken;
    private final UserRepository userRepository;
    private final StudentAnswerRepository studentAnswerRepository;
    private final QuestionSolvingTimeRepository questionSolvingTimeRepository;
    private final RestApiManager restApiManager;

    @Autowired
    public RestApiController(QuestionRepository questionRepository, DataMapper mapper, JwtToken jwtToken, UserRepository userRepository, StudentAnswerRepository studentAnswerRepository, QuestionSolvingTimeRepository questionSolvingTimeRepository, RestApiManager restApiManager) {
        this.questionRepository = questionRepository;
        this.mapper = mapper;
        this.jwtToken = jwtToken;
        this.userRepository = userRepository;
        this.studentAnswerRepository = studentAnswerRepository;
        this.questionSolvingTimeRepository = questionSolvingTimeRepository;
        this.restApiManager = restApiManager;
    }

    @RequestMapping(value = BASE_ROUTE + "/token/{encryptedUserId}", method = RequestMethod.GET)
    private String getJwtToken(@PathVariable String encryptedUserId, Authentication authentication) {
        return getJwtToken(encryptedUserId);

    }

    public String getJwtToken(String encryptedUserId) {
        long userId = 0;
        try {
            userId = Long.parseLong(Encryption.decrypt(encryptedUserId));
            User user = userRepository.findById(userId)
                    .orElse(null);
            if (user != null) {
                return jwtToken.createToken(userId, user.getRole().name());
            }
        } catch (EncryptionOperationNotPossibleException e) {
            //not matched
            return "";
        }

        return "";
    }


    @RequestMapping(value = BASE_ROUTE + "/question/{questionId}/answer/{answer}", method = RequestMethod.GET)
    private boolean isAnswerCorrect(@PathVariable long questionId, @PathVariable String answer, Authentication authentication, HttpServletRequest request) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    boolean answerIsCorrect = Objects.equals(answer, question.getAnswer());
                    StudentAnswer studentAnswer = StudentAnswer.builder()
                            .answer(question.getAnswer())
                            .attendedOn(new Date())
                            .question(question)
                            .gotCorrect(answerIsCorrect)
                            .build();
                    studentAnswerRepository.save(studentAnswer);
                    return answerIsCorrect;
                }).orElse(false);
    }

    @RequestMapping(value = BASE_ROUTE + "/question/{questionId}/time-count/{duration}/action/{action}", method = RequestMethod.GET)
    private void recordPerQuestionTime(HttpServletRequest request, @PathVariable long questionId, @PathVariable int duration, @PathVariable String action) {
        QuestionSolvingTime questionSolvingTime = QuestionSolvingTime.builder()
                .questionId(questionId)
                .action(action)
                .duration(duration)
                .createDate(new Date())
                .userId(restApiManager.getUserId(request))
                .build();
        questionSolvingTimeRepository.save(questionSolvingTime);
    }


    @RequestMapping(value = BASE_ROUTE + "/questions/{limit}/subject/{subjectId}", method = RequestMethod.GET)
    private QuestionsData randomQuestions(@PathVariable int limit, @PathVariable long subjectId) {
        int totalQuestions = questionRepository.countBySubjectId(subjectId);

        //TODO random question which are not answered by the student
        List<Question> questions = questionRepository.findBySubjectId(subjectId).stream()
                .limit(10)
                .collect(Collectors.toList());

        return mapper.from(questions);
    }

    @RequestMapping(value = BASE_ROUTE + "/questions/{limit}/subject/{subjectId}/etoken", method = RequestMethod.GET)
    private QuestionsData randomQuestionsWithEncrypted(@PathVariable int limit, @PathVariable long subjectId, @RequestParam String encryptedUserId, HttpServletResponse response) {
        response.addHeader("jwtToken", getJwtToken(encryptedUserId));
        // response.addHeader("Access-Control-Expose-Headers", "jwtToken");
        int totalQuestions = questionRepository.countBySubjectId(subjectId);

        //TODO random question which are not answered by the student
        List<Question> questions = questionRepository.findBySubjectId(subjectId).stream()
                .limit(10)
                .collect(Collectors.toList());

        return mapper.from(questions);
    }
}
