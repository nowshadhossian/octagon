package com.kids.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum FlagMessageType {

    ANSWER_NOT_HERE(1),
    QUESTION_IS_WRONG(2),
    OTHER(9);

    private int id;

    public static FlagMessageType getById(int flagId) {
        return Arrays.stream(FlagMessageType.values())
                .filter(flagMessageType -> flagMessageType.getId() == flagId)
                .findFirst()
                .orElse(FlagMessageType.OTHER);
    }
}
