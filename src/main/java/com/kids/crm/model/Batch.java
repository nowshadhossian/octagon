package com.kids.crm.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"subject_id" , "session_id", "teacher_id"})})
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

    @OneToMany(mappedBy = "batch", fetch = FetchType.EAGER)
    private Set<StudentBatch> studentBatches;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    public List<Student> getStudents(){
        return studentBatches.stream()
                .map(StudentBatch::getStudent)
                .collect(Collectors.toList());
    }

}
