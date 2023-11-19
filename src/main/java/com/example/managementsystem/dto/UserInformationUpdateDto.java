package com.example.managementsystem.dto;

import java.time.LocalDate;

public record UserInformationUpdateDto(String username, String password, String firstname,
                                       String lastname, String email, String phone, int age,
                                       LocalDate birthday) {
}
