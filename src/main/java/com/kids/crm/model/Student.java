package com.kids.crm.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "student")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Student extends User{
    private String phone;
    private String address;
    private Date dateOfBirth;
    private String gender;
    private String school;

    @ManyToMany
    @JoinTable(
            name="student_batch",
            joinColumns = @JoinColumn(name="student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "batch_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "batch_id"})
    )
    private List<Batch> batches;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<StudentBatchInterest> studentBatchInterests;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<StudentReferee> referees;

    public void addToBatch(Batch batch){
        batches.add(batch);
    }

    public void removeFromBatch(Batch batch){
        batches.remove(batch);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Student)) {
            return false;
        } else {
            Student other = (Student)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                return this.getId() == other.getId();
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Student;
    }

    public int hashCode() {
        int result = 1;
        long id = this.getId();
        result = result * 59 + (int)(id >>> 32 ^ id);
        return result;
    }

}
