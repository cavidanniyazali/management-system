package com.example.managementsystem.controller;

import com.example.managementsystem.dto.UserDto;
import com.example.managementsystem.dto.UserInformationUpdateDto;
import com.example.managementsystem.dto.UserRegisterDto;
import com.example.managementsystem.dto.UserSearchDto;
import com.example.managementsystem.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> findAll(Pageable pageable) {
        log.info("Get all users");
        return userServiceImpl.getUsers(pageable);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDto findById(@PathVariable Long id) {
        log.info("found user with id");
        return userServiceImpl.getUserById(id);
    }

    @GetMapping(value = "user/{username}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDto findByUsername(@PathVariable String username) {
        log.info("found user with username");
        return userServiceImpl.getUserByUsername(username);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserRegisterDto userRegisterDto) {
        log.info("added user");
        userServiceImpl.addUser(userRegisterDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long id, @RequestBody UserInformationUpdateDto userInformationUpdateDto) {
        log.info("updated user with id");
        userServiceImpl.updateUser(id, userInformationUpdateDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) {
        log.info("deleted user with id");
        userServiceImpl.deleteUser(id);
    }

    @GetMapping(path = "/search")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserSearchDto> searchUser(UserSearchDto userSearchDto) {
        log.info("searched user");
        return userServiceImpl.searchUser(userSearchDto);
    }
}
