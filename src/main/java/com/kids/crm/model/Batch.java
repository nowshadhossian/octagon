package com.kids.crm.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table
@Entity(name = "batch")
public class Batch{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name="session_id")
    private Session session;

    @ManyToMany(mappedBy = "batches")
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

}
