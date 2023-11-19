package com.example.managementsystem.service;

import com.example.managementsystem.dto.UserDto;
import com.example.managementsystem.dto.UserInformationUpdateDto;
import com.example.managementsystem.dto.UserRegisterDto;
import com.example.managementsystem.dto.UserSearchDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getUsers(Pageable pageable);

    UserDto getUserByUsername(String username);

    UserDto getUserById(long id);

    UserRegisterDto addUser(UserRegisterDto userRegisterDto);

    UserInformationUpdateDto updateUser(Long id, UserInformationUpdateDto userInformationUpdateDto);

    void deleteUser(Long id);

    List<UserSearchDto> searchUser(UserSearchDto userSearchDto);
}
