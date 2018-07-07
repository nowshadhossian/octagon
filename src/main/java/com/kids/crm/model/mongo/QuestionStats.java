package com.kids.crm.model.mongo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class QuestionStats {
    private String questionId;
    private String skipCount;
    private String flagCount;
    private Date createDate;
    private List<Integer> flagMessages;
}
