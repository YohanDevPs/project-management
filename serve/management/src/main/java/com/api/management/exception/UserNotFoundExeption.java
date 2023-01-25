package com.api.management.exception;

public class UserNotFoundExeption extends RuntimeException{

    public UserNotFoundExeption(String msg) {
        super(msg);
    }
}
