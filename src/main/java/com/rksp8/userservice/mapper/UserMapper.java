package com.rksp8.userservice.mapper;

import com.rksp8.userservice.dto.UserDto;
import com.rksp8.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);
}
