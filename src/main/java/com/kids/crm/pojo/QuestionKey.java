package com.kids.crm.pojo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"year", "questionNo"})
public class QuestionKey {
    private int year;
    private int questionNo;


    public static QuestionKey get(int year, int questionNo){
        return QuestionKey.builder()
                .year(year)
                .questionNo(questionNo)
                .build();
    }
}
