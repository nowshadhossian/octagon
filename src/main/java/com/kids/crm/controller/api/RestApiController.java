package com.kids.crm.controller.api;

import com.kids.crm.controller.api.data.QuestionsData;
import com.kids.crm.model.*;
import com.kids.crm.model.mongo.QuestionSolvingTime;
import com.kids.crm.mongo.repository.QuestionSolvingTimeRepository;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.service.Encryption;
import com.kids.crm.service.JwtToken;
import com.kids.crm.service.exception.UserNotFoundException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
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
    private String getJwtTokenResponse(@PathVariable String encryptedUserId, Authentication authentication) {
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
    private String isAnswerCorrect(@PathVariable long questionId, @PathVariable String answer, Authentication authentication, HttpServletRequest request) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    boolean answerIsCorrect = Objects.equals(answer, question.getAnswer());
                    StudentAnswer studentAnswer = StudentAnswer.builder()
                            .answer(answer)
                            .attendedOn(new Date())
                            .question(question)
                            .gotCorrect(answerIsCorrect)
                            .user(restApiManager.getUser())
                            .examType(ExamType.DAILY_EXAM)
                            .build();
                    studentAnswerRepository.save(studentAnswer);
                    return "{\"success\": \"" + question.getAnswer() +"\"}";
                }).orElseThrow(RuntimeException::new);
    }

    @RequestMapping(value = BASE_ROUTE + "/question/{questionId}/time-count/{duration}/action/{action}", method = RequestMethod.GET)
    private void recordPerQuestionTime(HttpServletRequest request, @PathVariable long questionId, @PathVariable int duration, @PathVariable String action) {
        QuestionSolvingTime questionSolvingTime = QuestionSolvingTime.builder()
                .questionId(questionId)
                .action(action)
                .duration(duration)
                .createDate(new Date())
                .userId(restApiManager.getUserId())
                .build();
        questionSolvingTimeRepository.save(questionSolvingTime);
    }


    @RequestMapping(value = BASE_ROUTE + "/questions/{limit}/subject/{subjectId}", method = RequestMethod.GET)
    private QuestionsData randomQuestions(@PathVariable int limit, @PathVariable long subjectId) {
        return mapper.from(randomQuestionId(subjectId, restApiManager.getUser()));
    }

    private List<Question> randomQuestionId(long subjectId, User user){
        Set<Long> studentAnswers = studentAnswerRepository.findByUser(user)
                .stream()
                .map(StudentAnswer::getQuestion)
                .map(Question::getId)
                .collect(Collectors.toSet());

        List<IdOnly> unAnsweredQuestions = questionRepository.findBySubjectId(subjectId) //TODO put question in redis
                .stream()
                .filter(idOnly -> !studentAnswers.contains(idOnly.getId()))
                .collect(Collectors.toList());

        if (unAnsweredQuestions.isEmpty()) {
            return Collections.emptyList();
        }

        if (unAnsweredQuestions.size() <= 10) {
            return questionRepository.findByIdIn(unAnsweredQuestions.stream()
                    .map(IdOnly::getId)
                    .collect(Collectors.toSet()));
        }

        Set<Long> randomQuestionIds = new HashSet<>();
        Random random = new Random();
        while(randomQuestionIds.size() < 10){
            int randomQuestionId = random.nextInt(unAnsweredQuestions.size());
            randomQuestionIds.add(unAnsweredQuestions.get(randomQuestionId).getId());
        }
        return questionRepository.findByIdIn(randomQuestionIds);
    }

    @RequestMapping(value = BASE_ROUTE + "/questions/{limit}/subject/{subjectId}/etoken", method = RequestMethod.GET)
    private QuestionsData randomQuestionsWithEncrypted(@PathVariable int limit, @PathVariable long subjectId, @RequestHeader String encryptedUserId, HttpServletResponse response, HttpServletRequest request) {
        String jwtToken = getJwtToken(encryptedUserId);
        response.addHeader("jwtToken", jwtToken);
        // response.addHeader("Access-Control-Expose-Headers", "jwtToken");
        User user = userRepository.findById(restApiManager.getUserId(jwtToken))
                .orElseThrow(() -> new UserNotFoundException(-1));
        return mapper.from(randomQuestionId(subjectId, user));
    }
}
