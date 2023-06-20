package com.example.managementsystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserDto(String username, String password, String firstname,
                      String lastname, String email, String phone, Integer age,
                      LocalDate birthday, LocalDateTime registeredAt, LocalDateTime updateOn) {
}