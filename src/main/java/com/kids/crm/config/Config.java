package com.kids.crm.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class Config {
    String examUiDomain;
    String companyName;
    boolean multipleAnswers;
    String resourceLocation;
    String uploadQuestionImageLocation;
    List<QuestionVariant> questionVariants = new ArrayList<>();
    List<Curriculum> curriculums = new ArrayList<>();
    private Student student;


    @Getter
    @Setter
    public static class Student {
        private boolean perCourseReferral;
    }

    @Getter
    @Setter
    public static class QuestionVariant {
        private int id;
        private String name;
    }

    @Getter
    @Setter
    public static class Curriculum {
        private int id;
        private String name;
    }
}
