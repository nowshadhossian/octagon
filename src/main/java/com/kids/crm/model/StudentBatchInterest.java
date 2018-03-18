package com.kids.crm.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "student_batch_interest")
public class StudentBatchInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="batch_id")
    private Batch batch;
    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;
}
