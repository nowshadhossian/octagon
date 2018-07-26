package com.kids.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Version {
    ENGLISH(0),
    BENGALI(1);

    private int id;

    public static Version getById(int id) {
        return Arrays.stream(values())
                .filter(version -> version.getId() == id)
                .findFirst()
                .orElse(Version.ENGLISH);
    }
}
