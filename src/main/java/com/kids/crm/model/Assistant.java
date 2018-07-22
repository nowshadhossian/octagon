package com.kids.crm.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "assistant")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Assistant extends User{
    private String phone;
    private String address;
}
