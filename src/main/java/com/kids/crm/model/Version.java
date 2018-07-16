package com.kids.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Version {
    ENGLISH(0),
    BENGALI(1);

    private int id;
}
