package com.kids.crm.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;
}
