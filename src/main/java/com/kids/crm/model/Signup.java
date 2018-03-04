package com.kids.crm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class Signup {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String Address;
    private String[] enrollingIds;
    private String session;
    private String email;
    private String password;
    private String confirmPassword;
    private String subjectName;
    private String refereesName;
    private String[] guardianName;
    private String[]contactNo;
    private String[] relation;
    private String[] guardianEmail;
}
