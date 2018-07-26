package com.kids.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum BatchStatusType {
    PENDING(0),
    PAID(1),
    ACTIVE(2);

    private int id;
}
