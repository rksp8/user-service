package com.rksp8.userservice.client;

import com.rksp8.userservice.dto.PostDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "post-service", url = "http://localhost:8081")
public interface PostServiceClient {

    @GetMapping("/posts/all/{author}")
    List<PostDto> getPostsByAuthor(@PathVariable String author);
}
