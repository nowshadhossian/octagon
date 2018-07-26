package com.kids.crm.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Objects;

@Converter
public class BatchStatusConverter implements AttributeConverter<BatchStatusType, Integer> {

    @Override public Integer convertToDatabaseColumn(BatchStatusType batchStatusType) {
        return batchStatusType == null ? null : batchStatusType.getId();
    }

    @Override public BatchStatusType convertToEntityAttribute(Integer batchStatusId) {
        return Arrays.stream(BatchStatusType.values())
                .filter(batchStatusType -> Objects.equals(batchStatusType.getId(), batchStatusId))
                .findAny()
                .orElse(BatchStatusType.PENDING);
    }
}
