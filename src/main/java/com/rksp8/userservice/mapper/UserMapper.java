package com.rksp8.userservice.mapper;

import com.rksp8.userservice.dto.PostDto;
import com.rksp8.userservice.dto.UserDto;
import com.rksp8.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "posts", target = "posts")
    UserDto toDto(User user, List<PostDto> posts);
}
