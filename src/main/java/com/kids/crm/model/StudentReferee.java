package com.kids.crm.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "student_referee")
public class StudentReferee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;
    private String refereeName;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;
}
