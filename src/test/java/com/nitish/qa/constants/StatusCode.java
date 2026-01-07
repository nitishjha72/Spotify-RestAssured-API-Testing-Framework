package com.nitish.qa.constants;

public enum StatusCode {

    OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    UNAUTHORIZED(401);

    public final int code;

    StatusCode(int code) {
        this.code = code;
    }
}
