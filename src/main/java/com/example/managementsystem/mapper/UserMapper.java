package com.example.managementsystem.mapper;

import com.example.managementsystem.dto.UserDto;
import com.example.managementsystem.dto.UserInformationUpdateDto;
import com.example.managementsystem.dto.UserRegisterDto;
import com.example.managementsystem.dto.UserSearchDto;
import com.example.managementsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring"
//        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
//        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    User userDtoToUser(UserRegisterDto userRegisterDto);

    UserRegisterDto userToUserRegisterDto(User user);
    UserDto userToUserDto(User user);
    UserInformationUpdateDto userToUserInformationDto(User user);
    UserSearchDto userToUserSearchDto(User user);

    void userDtoRequestToInstructor(@MappingTarget User user, UserInformationUpdateDto userInformationUpdateDto);
}
