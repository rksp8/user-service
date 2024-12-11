package com.rksp8.userservice.config.context;

public enum CustomHeader {

    USER_ID("x-user-id"),
    USERNAME("x-username");

    public final String val;

    CustomHeader(String val) {
        this.val = val;
    }
}