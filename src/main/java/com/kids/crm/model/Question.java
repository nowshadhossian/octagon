package com.kids.crm.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@Table
@Entity(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String answer;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="session_id")
    private Session session;
    private int year;
    private int questionNo;
    private String fileName;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.MERGE, CascadeType.ALL})
    @JoinTable(name = "question_sub_topic",
                joinColumns = @JoinColumn(name = "question_id"),
                inverseJoinColumns = @JoinColumn(name=  "sub_topic_id")
    )
    private Set<SubTopic> subTopics;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="topic_id")
    private Topic topic;
    private int paper;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="subject_id")
    private Subject subject;
    private String answerExplanation;
}
