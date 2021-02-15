package com.example.restapi.exception;

public class AccessDeniedException extends CustomException {

    private static final long serialVersionUID = -1116671122895194101L;

    public AccessDeniedException() {
        super(ErrorCode.UNAUTHORIZED_USER);
    }
}
