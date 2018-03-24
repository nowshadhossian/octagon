package com.kids.crm.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ExamType {
    NULL(0),
    DAILY_EXAM(1),
    CUSTOM_EXAM(2);

    private int id;

    public static ExamType getByValue(int id){
       return Arrays.stream(values())
                .filter(examType -> examType.getId() == id)
                .findFirst()
                .orElse(NULL);
    }
}
