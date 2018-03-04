package com.kids.crm.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ExamType {
    NULL(0),
    DAILY_EXAM(1),
    CUSTOM_EXAM(2);

    private int id;


}
