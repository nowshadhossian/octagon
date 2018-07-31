package com.kids.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum FlagMessageType {

    ANSWER_NOT_HERE(1, "The correct answer is not in the options"),
    QUESTION_IS_WRONG(2, "There is a mistake in the question"),
    ANSWER_NOT_ACTUAL(3, "The given correct answer is not the actual correct one"),
    QUESTION_NOT_RELEVANT(4, "This question is not relevant to my course syllabus"),
    OTHER(9, "Other");

    private int id;
    private String message;

    public static FlagMessageType getById(int flagId) {
        return Arrays.stream(FlagMessageType.values())
                .filter(flagMessageType -> flagMessageType.getId() == flagId)
                .findFirst()
                .orElse(FlagMessageType.OTHER);
    }
}
