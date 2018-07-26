package com.kids.crm.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private String examsCurriculum;
    private Version version;
    private String referral;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<StudentBatch> studentBatches;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<StudentBatchInterest> studentBatchInterests;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<StudentReferee> referees;


    public List<Batch> getBatches(){
        return studentBatches.stream()
                .map(StudentBatch::getBatch)
                .collect(Collectors.toList());
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
