package com.kids.crm.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Objects;

@Converter
public class VersionConverter implements AttributeConverter<Version, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Version version) {
        return version == null ? null : version.getId();
    }

    @Override
    public Version convertToEntityAttribute(Integer versionId) {
        return Arrays.stream(Version.values())
                .filter(version -> Objects.equals(version.getId(), versionId))
                .findAny()
                .orElse(Version.ENGLISH);
    }
}
