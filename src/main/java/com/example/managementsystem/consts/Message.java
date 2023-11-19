package com.example.managementsystem.consts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Message {
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_NOT_FOUND_MSG = "Provided user not found";
    public static final String USER_ALREADY_EXIST = "User already exist";
    public static final String USER_ALREADY_EXIST_MSG = "Provided user already exist";
}
