package com.kids.crm.controller.api.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * Response Sample
 {
     "timesAnswered": 200,
     "options" : [
         5,
         32,
         50,
         13
     ]
 }
 */

@Getter
@Setter
@Builder
public class AnswerStatsData {
    private long timesAnswered;
    private List<Integer> options;
}
