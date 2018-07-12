package com.kids.crm.controller.api.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AnswerData {
    private List<String> correctOption;
    private String explanation;
}
