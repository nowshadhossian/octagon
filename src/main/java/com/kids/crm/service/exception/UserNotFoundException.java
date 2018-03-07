package com.kids.crm.service.exception;



public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long userId) {
        super("User Not found userId: " + userId);
    }
}
