package com.kids.crm.pojo;

import com.kids.crm.model.Student;
import com.kids.crm.model.User;
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
    private User user;//Student

    public void incrementCorrect(){
        setCorrect(getCorrect() + 1);
    }

    public void incrementWrong(){
        setWrong(getWrong() + 1);
    }
}
