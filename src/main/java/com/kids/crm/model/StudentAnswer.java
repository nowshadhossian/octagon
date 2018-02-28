package com.kids.crm.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "student_answer")
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="question_id")
    private Question question;
    private String answer;
    private Date attendedOn;
    private boolean gotCorrect;
}
