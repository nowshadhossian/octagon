package com.kids.crm.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class LastAttendedResult {
    private int correct;
    private int wrong;
    private Date date;
}
