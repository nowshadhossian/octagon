package com.kids.crm.pojo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamSettingsDTO {
    private long batchId;
    private int examTypeId;
    private int totalQuestion;

    //custom Exam
    private long topicId;
    private int year;
    private boolean showAnswersInTheEnd;

}
