package com.kids.crm.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "teacher")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Teacher extends User{
    private String phone;
    private String address;
    private String degree;

    @OneToMany(mappedBy = "teacher")
    private Set<Batch> batches;
}
