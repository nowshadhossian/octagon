package com.kids.crm.service.exception;


import com.kids.crm.model.User;

import java.util.function.Supplier;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long userId) {
        super("User Not found userId: " + userId);
    }
}
