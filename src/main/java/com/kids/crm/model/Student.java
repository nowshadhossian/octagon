package com.kids.crm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table
@Entity(name = "student")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Student extends User{
    private String phone;
    private String address;

    @ManyToMany
    @JoinTable(
            name="student_subject",
            joinColumns = @JoinColumn(name="student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id")
    )
    private List<Subject> subjects;

}
