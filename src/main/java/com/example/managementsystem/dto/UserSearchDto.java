package com.example.managementsystem.dto;

import java.util.Date;

public record UserSearchDto(String username, String firstname, String lastname,
                            String email, String phone, Integer age, Date birthday) {
}