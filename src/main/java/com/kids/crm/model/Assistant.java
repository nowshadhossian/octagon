package com.kids.crm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@Table
@Entity(name = "assistant")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Assistant extends User{
    private String phone;
    private String address;
}
