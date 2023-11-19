package com.example.managementsystem.exceptions.exception;

public class UserAlreadyExist extends GeneralException{
    public UserAlreadyExist(String code, String message) {
        super(code, message);
    }
}