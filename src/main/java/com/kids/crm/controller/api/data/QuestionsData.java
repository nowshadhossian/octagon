package com.kids.crm.controller.api.data;

import java.util.ArrayList;
import java.util.List;

public class QuestionsData {

    private List<QuestionData> questions = new ArrayList<>();

    public List<QuestionData> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionData> questions) {
        this.questions = questions;
    }

}
