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
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="subject_id")
    private Subject subject;
    private String answer;
    private Date attendedOn;
    private boolean gotCorrect;
    private ExamType examType;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="batch_id")
    private Batch batch;
}
