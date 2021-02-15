package com.example.restapi.exception;

public class UserNotFoundException extends CustomException {

    private static final long serialVersionUID = -3116671122895194101L;

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
