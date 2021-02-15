package com.example.restapi.exception;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
public enum ErrorCode {

    // common
    INVALID_PARAMETER(400, null, "Invalid Request Data"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    ALREADY_EXISTS_DATA(409, "C003", "이미 존재하는 데이터입니다."),

    // user 관련
    USER_NOT_FOUND(404, "U001", "사용자가 없습니다."),
    UNAUTHORIZED_USER(401, "U002", "잘못된 인증입니다.");



    private final int httpStatus;
    private final String errCode;
    private final String errMessage;

    ErrorCode(int httpStatus, String errCode, String errMessage) {
        this.httpStatus = httpStatus;
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
