package com.kids.crm.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table
@EqualsAndHashCode(of = {"id"}, callSuper = true)
@Entity(name = "student")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Student extends User{
    private String phone;
    private String address;

    @ManyToMany
    @JoinTable(
            name="student_batch",
            joinColumns = @JoinColumn(name="student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "batch_id", referencedColumnName = "id")
    )
    private List<Batch> batches;

    public void addToBatch(Batch batch){
        batches.add(batch);
    }

}
