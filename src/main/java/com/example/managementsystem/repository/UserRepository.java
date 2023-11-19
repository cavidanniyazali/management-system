package com.example.managementsystem.repository;

import com.example.managementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Query(value =
            "UPDATE User u SET u.username = :username, u.password = :password, u.firstname = :firstname, u.lastname = :lastname, u.email = :email, u.phone = :phone, u.age = :age, u.birthday = :birthday WHERE u.id = :id")
    void updateUserById(@Param("id") Long id,
                        @Param("username") String username,
                        @Param("password") String password,
                        @Param("firstname") String firstname,
                        @Param("lastname") String lastname,
                        @Param("email") String email,
                        @Param("phone") String phone,
                        @Param("age") int age,
                        @Param("birthday") LocalDate birthday);
}
