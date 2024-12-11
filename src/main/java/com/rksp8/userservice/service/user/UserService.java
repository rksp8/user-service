package com.rksp8.userservice.service.user;

import com.rksp8.userservice.dto.UserDto;

import java.util.List;

public interface UserService {

    void createUser(String username);

    UserDto getUserByUsername(String username);

    List<UserDto> getAllUsers();

    void deleteUserByUsername(String username);

    UserDto getCurrentUser();

}
