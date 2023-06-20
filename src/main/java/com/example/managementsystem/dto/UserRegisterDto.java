package com.example.managementsystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserRegisterDto(String username, String password, String firstname,
                              String lastname, String email, String phone, int age,
                              LocalDate birthday, LocalDateTime registeredAt, boolean isActive) {
}
