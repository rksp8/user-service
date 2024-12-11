package com.rksp8.userservice.dto;

import com.rksp8.userservice.entity.Provider;
import com.rksp8.userservice.entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Provider provider;
    private Role role;
}
