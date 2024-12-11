package com.rksp8.userservice.config.context;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UsernameContext {

    private final ThreadLocal<String> context = new ThreadLocal<>();

    public void setUsername(String username) {
        context.set(username);
    }

    public String getUsername() {
        return context.get();
    }

    public void clear() {
        context.remove();
    }
}