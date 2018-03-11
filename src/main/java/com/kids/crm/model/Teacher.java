package com.kids.crm.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Getter
@Setter
@Builder
@Table
@Entity(name = "teacher")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Teacher extends User{
    private String phone;
    private String address;
    private String degree;
}
