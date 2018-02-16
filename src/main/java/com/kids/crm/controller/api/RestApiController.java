package com.kids.crm.controller.api;

import com.kids.crm.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class RestApiController {
    private static final String BASE_ROUTE = "/api";

    private final QuestionRepository questionRepository;

    @Autowired
    public RestApiController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = BASE_ROUTE + "/question/{questionId}/answer/{answer}", method = RequestMethod.GET)
    private boolean isAnswerCorrect(@PathVariable long questionId, @PathVariable String answer) {
        return questionRepository.findById(questionId)
                .map(question -> Objects.equals(answer, question.getAnswer()))
                        .orElse(false);
    }
}
