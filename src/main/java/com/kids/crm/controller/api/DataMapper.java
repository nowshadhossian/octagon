package com.kids.crm.controller.api;

import com.kids.crm.controller.api.data.QuestionData;
import com.kids.crm.controller.api.data.QuestionsData;
import com.kids.crm.model.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataMapper {
    public QuestionsData from(List<Question> source){
        QuestionsData questionsData = new QuestionsData();
        List<QuestionData> list = new ArrayList<>();
        source.forEach(question -> {
            QuestionData questionData = toQuestionData(question);
            list.add(questionData);
        });
        questionsData.setQuestions(list);
        return questionsData;
    }

    private QuestionData toQuestionData(Question src) {
        QuestionData to = new QuestionData();
        to.setId(src.getId());
        to.setPicUrl(src.getFileName());
        return to;
    }
}
