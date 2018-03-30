package com.kids.crm.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {
    TEACHER("ROLE_TEACHER"),
    STUDENT("ROLE_STUDENT"),
    ASSISTANT("ROLE_ASSISTANT"),
    SUPER_ADMIN("ROLE_SUPER_ADMIN");

    private String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public boolean isStudent() {
        return this == STUDENT;
    }

    public boolean isTeacher() {
        return this == TEACHER;
    }

    public String getNameStripped(){
        return getName().substring(5);
    }
}
