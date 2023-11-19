package com.example.managementsystem.exceptions.handler;

import com.example.managementsystem.exceptions.ExceptionResponse;
import com.example.managementsystem.exceptions.exception.UserAlreadyExist;
import com.example.managementsystem.exceptions.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandle {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RuntimeException.class})
    public ExceptionResponse handleUnexpectedError(RuntimeException ex) {
        log.error("Action.handleValidationException.error validate exception: {}", ex.toString());
        return new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionResponse handleNotFoundException(UserNotFoundException ex) {
        log.error("Action.handleNotFoundException.error not found exception: {}", ex.toString());
        return new ExceptionResponse(ex.getCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExist.class)
    public ExceptionResponse handleNotFoundException(UserAlreadyExist ex) {
        log.error("Action.handleNotFoundException.error already exist exception: {}", ex.toString());
        return new ExceptionResponse(ex.getCode(), ex.getMessage());
    }
}
