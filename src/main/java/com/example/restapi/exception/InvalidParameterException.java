package com.example.restapi.exception;

import org.springframework.validation.Errors;

public class InvalidParameterException extends CustomException {

    private static final long serialVersionUID = -2116671122895194101L;

    private final Errors errors;

    // @Validate 대한 결과를 매개변수 BindingResult 로 받음. BindingResult 는 Errors 클래스를 상속한다.
    public InvalidParameterException(Errors errors) {
        super(ErrorCode.INVALID_PARAMETER);
        this.errors = errors;
    }

    public Errors getErrors() {
        return this.errors;
    }
}
