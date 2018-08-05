package com.kids.crm.controller.api;

import com.kids.crm.config.Config;
import com.kids.crm.controller.api.data.AnswerData;
import com.kids.crm.controller.api.data.AnswerStatsData;
import com.kids.crm.controller.api.data.QuestionsData;
import com.kids.crm.model.*;
import com.kids.crm.model.mongo.QuestionSolvingTime;
import com.kids.crm.model.mongo.QuestionStats;
import com.kids.crm.mongo.repository.QuestionSolvingTimeRepository;
import com.kids.crm.pojo.ExamSettingsDTO;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.service.*;
import com.kids.crm.service.exception.UserNotFoundException;
import com.kids.crm.utils.Utils;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    private final QuestionService questionService;
    private final Config config;
    private final QuestionStatService questionStatService;
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private final StudentAnswerService studentAnswerService;

    @Autowired
    public RestApiController(QuestionRepository questionRepository, DataMapper mapper, JwtToken jwtToken, UserRepository userRepository, StudentAnswerRepository studentAnswerRepository, QuestionSolvingTimeRepository questionSolvingTimeRepository, RestApiManager restApiManager, QuestionService questionService, Config config, QuestionStatService questionStatService, ThreadPoolTaskExecutor threadPoolTaskExecutor, StudentAnswerService studentAnswerService) {
        this.questionRepository = questionRepository;
        this.mapper = mapper;
        this.jwtToken = jwtToken;
        this.userRepository = userRepository;
        this.studentAnswerRepository = studentAnswerRepository;
        this.questionSolvingTimeRepository = questionSolvingTimeRepository;
        this.restApiManager = restApiManager;
        this.questionService = questionService;
        this.config = config;
        this.questionStatService = questionStatService;
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.studentAnswerService = studentAnswerService;
    }

   /* @RequestMapping(value = BASE_ROUTE + "/token/{encryptedUserId}", method = RequestMethod.GET)
    private String getJwtTokenResponse(@PathVariable String encryptedUserId, Authentication authentication) {
        return getJwtToken(encryptedUserId);

    }*/

    public String getJwtToken(String encryptedUserId, String examSettingsDtoEncrypted) {
        long userId = 0;
        try {
            userId = Long.parseLong(Encryption.decrypt(encryptedUserId));
            User user = userRepository.findById(userId)
                    .orElse(null);
            if (user != null) {
                return jwtToken.createToken(userId, user.getRole().name(), examSettingsDtoEncrypted);
            }
        } catch (EncryptionOperationNotPossibleException e) {
            //not matched
            return "";
        }

        return "";
    }


    @RequestMapping(value = BASE_ROUTE + "/question/{questionId}/answer/{answer}", method = RequestMethod.GET)
    private AnswerData isAnswerCorrect(@PathVariable long questionId, @PathVariable String answer, Authentication authentication, HttpServletRequest request) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    boolean answerIsCorrect = Utils.answerMatched(answer, question.getAnswer());
                    StudentAnswer studentAnswer = studentAnswerService.findByUserAndBatchAndQuestion(restApiManager.getUser(), restApiManager.getCurrentBatch(), question)
                            .orElseGet(() -> StudentAnswer.builder()
                                    .answer(answer)
                                    .attendedOn(new Date())
                                    .question(question)
                                    .subject(question.getSubject())
                                    .batch(restApiManager.getCurrentBatch())
                                    .gotCorrect(answerIsCorrect)
                                    .user(restApiManager.getUser())
                                    .examType(ExamType.getByValue(restApiManager.getExamSettingsDTO().getExamTypeId()))
                                    .build());
                    studentAnswerRepository.save(studentAnswer);

                    threadPoolTaskExecutor.execute(() -> {
                        QuestionStats questionStats = questionStatService.findQuestionStatByQuestionId(questionId)
                                .orElse(QuestionStats.builder()
                                        .questionId(questionId)
                                        .build());
                        if (answerIsCorrect) {
                            questionStats.incrementCorrectCount(answer);
                        } else {
                            questionStats.incrementWrongCount(answer);
                        }
                        questionStatService.save(questionStats);
                    });

                    return AnswerData.builder()
                            .correctOption(Arrays.asList(question.getAnswer().split(",")))
                            .explanation(question.getAnswerExplanation())
                            .build();
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

    private List<Question> randomQuestionId(long subjectId, User user) {
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
        while (randomQuestionIds.size() < 10) {
            int randomQuestionId = random.nextInt(unAnsweredQuestions.size());
            randomQuestionIds.add(unAnsweredQuestions.get(randomQuestionId).getId());
        }
        return questionRepository.findByIdIn(randomQuestionIds);
    }

    private List<Question> randomQuestionId(long subjectId, User user, ExamSettingsDTO examSettingsDTO) {


        Set<Long> studentAnswers = studentAnswerRepository.findByUser(user)
                .stream()
                .map(StudentAnswer::getQuestion)
                .map(Question::getId)
                .collect(Collectors.toSet());

        Iterable<Question> questions = questionService.findQuestionsByExamSettings(subjectId, examSettingsDTO);
        List<Long> unAnsweredQuestions = StreamSupport.stream(questions.spliterator(), false)
                .filter(question -> examSettingsDTO.getQuestionNo() > 0 || !studentAnswers.contains(question.getId()))//if questionNo is included add it to unanswered. And Begin exam
                .map(Question::getId)
                .collect(Collectors.toList());

        if (unAnsweredQuestions.isEmpty()) {
            return Collections.emptyList();
        }

        if (unAnsweredQuestions.size() <= 10) {
            return questionRepository.findByIdIn(new HashSet<>(unAnsweredQuestions));
        }


        Set<Long> randomQuestionIds = new HashSet<>();
        Random random = new Random();
        while (randomQuestionIds.size() < examSettingsDTO.getTotalQuestion()) {
            int randomQuestionId = random.nextInt(unAnsweredQuestions.size());
            randomQuestionIds.add(unAnsweredQuestions.get(randomQuestionId));
        }
        return questionRepository.findByIdIn(randomQuestionIds);
    }

    @RequestMapping(value = BASE_ROUTE + "/question/{questionId}/answer/stats", method = RequestMethod.GET)
    private AnswerStatsData retrieveAnswerStats(HttpServletRequest request, @PathVariable long questionId) {
        QuestionStats questionStats = questionStatService.findQuestionStatByQuestionId(questionId).orElse(QuestionStats.builder()
                .timesAnsweredCount(0)
                .answeredCountWithOption(Map.of("A", 0, "B", 0, "C", 0, "D", 0))
                .build());

        return AnswerStatsData.builder()
                .timesAnswered(questionStats.getTimesAnsweredCount())
                .options(new ArrayList<>(questionStats.getAnsweredCountWithOption().values()))
                .build();

    }

    @RequestMapping(value = BASE_ROUTE + "/question/{questionId}/flag/{flagValue}", method = RequestMethod.GET)
    private void saveQuestionFlag(HttpServletRequest request, @PathVariable long questionId, @PathVariable int flagValue) {
        QuestionStats questionStats = questionStatService.findQuestionStatByQuestionId(questionId).orElse(QuestionStats.builder()
                .questionId(questionId)
                .build());

        questionStats.incrementFlagCount(FlagMessageType.getById(flagValue));
        questionStatService.save(questionStats);
    }


    @RequestMapping(value = BASE_ROUTE + "/questions/subject/etoken", method = RequestMethod.GET)
    private QuestionsData randomQuestionsWithEncrypted(@RequestHeader String encryptedUserId, @RequestHeader String examSettingsDtoEncrypted, HttpServletResponse response, HttpServletRequest request) {
        String jwtToken = getJwtToken(encryptedUserId, examSettingsDtoEncrypted);
        response.addHeader("jwtToken", jwtToken);
        // response.addHeader("Access-Control-Expose-Headers", "jwtToken");
        User user = userRepository.findById(restApiManager.getUserId(jwtToken)) //Authorization header is not available yet
                .orElseThrow(() -> new UserNotFoundException(-1));
        QuestionsData questionsData;
        if (ExamType.getByValue(restApiManager.getExamSettingsDTO().getExamTypeId()) == ExamType.DAILY_EXAM) {
            questionsData = mapper.from(randomQuestionId(restApiManager.getCurrentBatch().getSubject().getId(), user));
        } else {
            questionsData = mapper.from(randomQuestionId(restApiManager.getCurrentBatch().getSubject().getId(), user, restApiManager.getExamSettingsDTO()));
        }

        questionsData.setSettings(config.isMultipleAnswers(), restApiManager.getExamSettingsDTO().isShowAnswersInTheEnd());
        return questionsData;


    }
}
