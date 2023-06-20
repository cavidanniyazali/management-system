package com.example.managementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users",schema = "devzone")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    Long id;

    @NotNull(message = "Username may not be null")
    @NotEmpty(message = "Username may not be empty")
    @NotBlank(message = "Username is required")
    @Size(min = 5, max = 10)
    @Column(name = "username", unique = true, nullable = false)
    String username;

    @NotNull(message = "Password may not be null")
    @NotBlank(message = "Password is required")
    @NotEmpty(message = "Password may not be empty")
    @Size(min = 7)
    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "firstname")
    String firstname;

    @Column(name = "lastname")
    String lastname;

    @Email(message = "Invalid email")
    @Column(name = "email")
    String email;

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$",
            message = "Invalid phone number")
    @Column(name = "phone")
    String phone;

    @Positive
    @Column(name = "age")
    int age;

    @Column(name = "birthday")
    LocalDate birthday;

    @CreationTimestamp
    @Column(name = "registered_at")
    LocalDateTime registeredAt;

    @UpdateTimestamp
    @Column(name = "updated_on")
    LocalDateTime updateOn;

    @NotEmpty
    @Column(name = "is_active", nullable = false)
    boolean isActive;

//    @PrePersist
//    void createdAt() {
//        this.registeredAt = new DateTimeFormatter();
//    }
}
