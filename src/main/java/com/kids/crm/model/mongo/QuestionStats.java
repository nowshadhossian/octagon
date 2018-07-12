package com.kids.crm.model.mongo;

import com.kids.crm.model.FlagMessageType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@Builder
@Document
public class QuestionStats {
    @Id
    private String questionId;
    private int skipCount;
    private int flagCount;
    private Date createDate;
    private Map<FlagMessageType, Integer> flagMessageCount;

    private int timesAnsweredCount;
    private Map<String, Integer> answeredCountWithOption; //A: 30, B: 50..

}
