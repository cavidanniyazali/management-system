package com.example.managementsystem.exceptions.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class GeneralException extends RuntimeException {
    private final String code;
    private final String message;
}