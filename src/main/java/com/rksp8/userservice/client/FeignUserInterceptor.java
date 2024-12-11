package com.rksp8.userservice.client;

import com.rksp8.userservice.config.context.UsernameContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeignUserInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("x-username", String.valueOf(UsernameContext.getUsername()));
    }
}