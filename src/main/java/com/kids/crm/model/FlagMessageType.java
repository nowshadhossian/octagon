package com.kids.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FlagMessageType {

    ANSWER_NOT_HERE(1),
    QUESTION_IS_WRONG(2),
    OTHER(9);

    private int id;

}
