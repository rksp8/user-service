package com.rksp8.userservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private List<PostDto> posts;
    private List<RatingDto> ratings;
}