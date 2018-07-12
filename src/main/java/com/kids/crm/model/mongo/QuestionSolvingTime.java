package com.kids.crm.model.mongo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;


/**
 * User specific question stats.
 */

@Getter
@Setter
@Builder
@Document
public class QuestionSolvingTime {
    @Id
    private String id;
    private long questionId;
    private long userId;
    private String action;
    private int duration;
    private Date createDate;

}
