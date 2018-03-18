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
    @JoinColumn(name="batch_id")
    private Batch batch;
    private String refereeName;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;
}
