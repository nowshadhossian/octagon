package com.kids.crm.service.exception;


import com.kids.crm.model.User;

import java.util.function.Supplier;

public class UserNotFoundException extends RuntimeException implements Supplier<UserNotFoundException> {
    private long userId;

    public UserNotFoundException(long userId) {
        super("User Not found userId: " + userId);
        this.userId = userId;
    }

    @Override
    public UserNotFoundException get() {
        return new UserNotFoundException(userId);
    }
}
