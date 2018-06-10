package com.kids.crm.controller.api.data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuestionsData {

    private List<QuestionData> questions = new ArrayList<>();

    private Settings settings;

    public void setSettings(boolean multipleAnswers){
        settings = new Settings();
        settings.setMultipleAnswers(multipleAnswers);
    }

   @Getter
   @Setter
   private class Settings {
       private boolean multipleAnswers;
   }

}
