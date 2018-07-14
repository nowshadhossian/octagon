package com.kids.crm.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Objects;

@Converter
public class FlagMessageConverter implements AttributeConverter<FlagMessageType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(FlagMessageType flagMessageType) {
        return flagMessageType == null ? null : flagMessageType.getId();
    }

    @Override
    public FlagMessageType convertToEntityAttribute(Integer flagMessageId) {
        return Arrays.stream(FlagMessageType.values())
                .filter(flagMessageType -> Objects.equals(flagMessageType.getId(), flagMessageId))
                .findAny()
                .orElse(FlagMessageType.OTHER);
    }
}
