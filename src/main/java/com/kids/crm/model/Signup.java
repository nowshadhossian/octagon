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
    private long[] enrollingIds;
    private String session;
    private String email;
    private String password;
    private String dateOfBirth;
    private String confirmPassword;
    private String school;
    private String gender;
    private long interestSessionId;
    private String examsCurriculum;
    private String referral;
    private long teacherId;
    private int version;

    //referees
    private long[] refereesSubjectId;
    private String[] refereesName;

    //guardian
    private String[] guardianName;
    private String[] guardianContactNo;
    private String[] guardianRelation;
    private String[] guardianEmail;

    //used in teacher registration
    private String degree;
}
