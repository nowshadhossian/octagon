package com.kids.crm.model;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Signup {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String address;
    private String[] enrollingIds;
    private String session;
    private String email;
    private String password;
    private String dateOfBirth;
    private String confirmPassword;
    private String school;
    private String[] subject;
    private String[] refereesName;
    private String[] guardianName;
    private String[]contactNo;
    private String[] relation;
    private String[] guardianEmail;

    //teacher
    private String degree;
}
