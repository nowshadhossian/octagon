package com.kids.crm.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    TEACHER("Teacher"),
    STUDENT("Student"),
    ASSISTANT("Assistant"),
    SUPER_ADMIN("Super_Admin");

    private String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
