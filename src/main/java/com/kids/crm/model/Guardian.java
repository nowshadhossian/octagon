package com.kids.crm.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "guardian")
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="student_id")
    private Student student;

    private String name;
    private String email;
    private String phone;
    private String relation;

}
