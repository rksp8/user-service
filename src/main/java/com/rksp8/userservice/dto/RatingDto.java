package com.rksp8.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {

    private Long id;
    private Integer rating;
    private String comment;
    private Long postId;
    private String author;
    private LocalDateTime createdAt;
}

