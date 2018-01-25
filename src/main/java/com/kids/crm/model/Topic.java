package com.kids.crm.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;
}
