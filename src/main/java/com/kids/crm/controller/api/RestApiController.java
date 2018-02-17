package com.kids.crm.controller.api;

import com.kids.crm.controller.api.data.QuestionsData;
import com.kids.crm.model.Question;
import com.kids.crm.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class RestApiController {
    private static final String BASE_ROUTE = "/api";

    private final QuestionRepository questionRepository;
    private final DataMapper mapper;

    @Autowired
    public RestApiController(QuestionRepository questionRepository, DataMapper mapper) {
        this.questionRepository = questionRepository;
        this.mapper = mapper;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = BASE_ROUTE + "/question/{questionId}/answer/{answer}", method = RequestMethod.GET)
    private boolean isAnswerCorrect(@PathVariable long questionId, @PathVariable String answer) {
        return questionRepository.findById(questionId)
                .map(question -> Objects.equals(answer, question.getAnswer()))
                        .orElse(false);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = BASE_ROUTE + "/questions/{limit}/subject/{subjectId}", method = RequestMethod.GET)
    private QuestionsData randomQuestions(@PathVariable int limit, @PathVariable long subjectId) {
        /*"questions": [
        {
            id: 2,
                    "isAnswered": false,
                'picUrl': 'question-sample.png',
                'selectedOption': null,
                'answerCorrect': null
        },*/


      int totalQuestions = questionRepository.countBySubjectId(subjectId);

        //TODO random question which are not answered by the student
         List<Question> questions = questionRepository.findBySubjectId(subjectId).stream()
                 .limit(10)
                 .collect(Collectors.toList());

        return mapper.from(questions);
    }
}
