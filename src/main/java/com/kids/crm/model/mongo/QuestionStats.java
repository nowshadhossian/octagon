package com.kids.crm.model.mongo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@Document
public class QuestionStats {
    private String questionId;
    private String skipCount;
    private String flagCount;
    private Date createDate;
    private List<Integer> flagMessages;
}
