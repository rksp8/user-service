package com.rksp8.userservice.mapper;

import com.rksp8.userservice.dto.UserDto;
import com.rksp8.userservice.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDto toDto(User user);
}
