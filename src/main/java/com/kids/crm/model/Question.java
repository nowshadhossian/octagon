package com.kids.crm.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String answer;
    @ManyToOne()
    @JoinColumn(name="session_id")
    private Session session;
    private int year;
    private int questionNo;
    private String fileName;
    @ManyToMany()
    @JoinTable(name = "question_sub_topic",
                joinColumns = @JoinColumn(name = "question_id"),
                inverseJoinColumns = @JoinColumn(name=  "sub_topic_id")
    )
    private Set<SubTopic> subTopics;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="topic_id")
    private Topic topic;
    private int paper;
    @ManyToOne()
    @JoinColumn(name="subject_id")
    private Subject subject;
    private String answerExplanation;
}
