package com.example.managementsystem.service.impl;

import com.example.managementsystem.consts.Message;
import com.example.managementsystem.dto.UserDto;
import com.example.managementsystem.dto.UserInformationUpdateDto;
import com.example.managementsystem.dto.UserRegisterDto;
import com.example.managementsystem.dto.UserSearchDto;
import com.example.managementsystem.entity.User;
import com.example.managementsystem.exceptions.exception.UserAlreadyExist;
import com.example.managementsystem.exceptions.exception.UserNotFoundException;
import com.example.managementsystem.mapper.UserMapper;
import com.example.managementsystem.repository.UserRepository;
import com.example.managementsystem.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.stream()
                .map(userMapper::userToUserDto)
                .toList();
    }

    @Override
    public UserDto getUserByUsername(String username) {
        Optional<User> userByUsername = userRepository.findByUsername(username);
        User user = userByUsername.orElseThrow(() -> new UserNotFoundException(Message.USER_NOT_FOUND,
                Message.USER_NOT_FOUND_MSG));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getUserById(long id) {
        Optional<User> userById = userRepository.findById(id);
        User user = userById.orElseThrow(() -> new UserNotFoundException(Message.USER_NOT_FOUND,
                Message.USER_NOT_FOUND_MSG));
        return userMapper.userToUserDto(user);
    }

    @Transactional
    @Override
    public UserRegisterDto addUser(UserRegisterDto userRegisterDto) {
        Optional<User> userByUsername = userRepository.findByUsername(userRegisterDto.username());
        if (userByUsername.isPresent()) {
            throw new UserAlreadyExist(Message.USER_ALREADY_EXIST, Message.USER_ALREADY_EXIST_MSG);
        }

        User user = userMapper.userDtoToUser(userRegisterDto);
        userRepository.save(user);
        return userRegisterDto;
    }

    @Transactional
    @Override
    public UserInformationUpdateDto updateUser(Long id, UserInformationUpdateDto userInformationUpdateDto) {
        Optional<User> userById = userRepository.findById(id);
        User user = userById.orElseThrow(() -> new UserNotFoundException(Message.USER_NOT_FOUND,
                Message.USER_NOT_FOUND_MSG));
        if (user.getUsername().equals(userInformationUpdateDto.username())) {
            throw new UserAlreadyExist(Message.USER_ALREADY_EXIST, Message.USER_ALREADY_EXIST_MSG);
        }
        userMapper.userDtoRequestToInstructor(user, userInformationUpdateDto);
        userRepository.updateUserById(user.getId(), user.getUsername(), user.getPassword(),
                user.getFirstname(), user.getLastname(), user.getEmail(), user.getPhone(),
                user.getAge(), user.getBirthday());
        return userMapper.userToUserInformationDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("user deleted");
    }

    @Override
    public List<UserSearchDto> searchUser(UserSearchDto userSearchDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        if (userSearchDto.username() != null) {
            Predicate usernamePredicate = criteriaBuilder.like(
                    root.get("username"),
                    "%" + userSearchDto.username() + "%");
            predicates.add(usernamePredicate);
        }

        if (userSearchDto.firstname() != null) {
            Predicate firstnamePredicate = criteriaBuilder.like(
                    root.get("firstname"),
                    "%" + userSearchDto.firstname() + "%");
            predicates.add(firstnamePredicate);
        }

        if (userSearchDto.lastname() != null) {
            Predicate lastnamePredicate = criteriaBuilder.like(
                    root.get("lastName"),
                    "%" + userSearchDto.lastname() + "%"
            );
            predicates.add(lastnamePredicate);
        }


        if (userSearchDto.email() != null) {
            Predicate emailPredicate = criteriaBuilder.like(
                    root.get("email"),
                    "%" + userSearchDto.email() + "%"
            );
            predicates.add(emailPredicate);
        }

        if (userSearchDto.phone() != null) {
            Predicate phonePredicate = criteriaBuilder.like(
                    root.get("phone"), "%" + userSearchDto.phone() + "%");
            predicates.add(phonePredicate);
        }

        if (userSearchDto.age() != null) {
            Predicate agePredicate = criteriaBuilder.like(
                    root.get("age"), "%" + userSearchDto.age() + "%"
            );
            predicates.add(agePredicate);
        }

        if (userSearchDto.birthday() != null) {
            Predicate birthdayPredicate = criteriaBuilder.like(
                    root.get("birthday"), "%" + userSearchDto.birthday() + "%"
            );
            predicates.add(birthdayPredicate);
        }

        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
        );
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        List<User> instructors = query.getResultList();
        return instructors.stream()
                .map(userMapper::userToUserSearchDto)
                .toList();
    }
}
