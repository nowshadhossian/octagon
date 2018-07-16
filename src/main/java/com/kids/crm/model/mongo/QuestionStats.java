package com.kids.crm.model.mongo;

import com.kids.crm.model.FlagMessageType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
@Document
public class QuestionStats {
    @Id
    private String id;
    private long questionId;
    private int skipCount;
    private int flagCount;
    @CreatedDate
    private Date createDate;
    private Map<FlagMessageType, Integer> flagMessageCount;

    private int timesAnsweredCount;//attemptedCount
    private Map<String, Integer> answeredCountWithOption; //A: 30, B: 50..

    private int correctCount;
    private int wrongCount;

    public void incrementSkipCount() {
        setSkipCount(getSkipCount() + 1);
    }

    private void incrementFlagCount() {
        setFlagCount(getFlagCount() + 1);
    }

    public void incrementFlagCount(FlagMessageType flagMessageType) {
        if (flagMessageCount == null) {
            flagMessageCount = new HashMap<>();
        }
        incrementFlagCount();
        flagMessageCount.put(flagMessageType, flagMessageCount.getOrDefault(flagMessageType, 0) + 1);
    }

    private void incrementAttempted() {
        setTimesAnsweredCount(getTimesAnsweredCount() + 1);
    }

    /**
     * @param optionChosen value can be B,A or D or A, D,B
     */
    public void incrementCorrectCount(String optionChosen) {
        incrementAttempted();
        setCorrectCount(getCorrectCount() + 1);
        addToOptionCount(optionChosen);
    }

    public void incrementWrongCount(String optionChosen) {
        incrementAttempted();
        setWrongCount(getWrongCount() + 1);
        addToOptionCount(optionChosen);
    }

    /**
     * @param optionChosen value can be B,A or D or A, D,B
     */
    private void addToOptionCount(String optionChosen) {
        if (answeredCountWithOption == null) {
            answeredCountWithOption = new HashMap<>();
        }
        Arrays.stream(optionChosen.split(","))
                .forEach(singleOption -> {
                            String singleTrimmed = singleOption.trim().toUpperCase();
                            answeredCountWithOption.put(singleTrimmed, answeredCountWithOption.getOrDefault(singleTrimmed, 0) + 1);
                        }
                );
    }


}
