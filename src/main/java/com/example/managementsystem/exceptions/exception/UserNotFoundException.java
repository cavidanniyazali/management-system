package com.example.managementsystem.exceptions.exception;

public class UserNotFoundException extends GeneralException {
    public UserNotFoundException(String code, String message) {
        super(code, message);
    }
}