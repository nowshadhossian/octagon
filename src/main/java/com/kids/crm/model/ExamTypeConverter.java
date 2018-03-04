package com.kids.crm.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Objects;

@Converter
public class ExamTypeConverter implements AttributeConverter<ExamType, Integer>{

    @Override public Integer convertToDatabaseColumn(ExamType examTypeId) {
        return examTypeId == null ? null : examTypeId.getId();
    }

    @Override public ExamType convertToEntityAttribute(Integer examTypeId) {
        return Arrays.stream(ExamType.values())
                .filter(moderatorActionType -> Objects.equals(moderatorActionType.getId(), examTypeId))
                .findAny()
                .orElse(ExamType.NULL);
    }
}
