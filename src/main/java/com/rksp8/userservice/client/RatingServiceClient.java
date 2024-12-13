package com.rksp8.userservice.client;

import com.rksp8.userservice.dto.RatingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "rating-service", url = "lb://rating-service")
public interface RatingServiceClient {

    @GetMapping("/rating/post/{postId}")
    List<RatingDto> getAllPostsRatings(@PathVariable Long postId);

}
