package com.project.dalda.common.aop;

import com.project.dalda.common.response.ErrorResponse;
import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
    final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    final String USER_ILLEGAL_STATE = "USER_ILLEGAL_STATE";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(Exception e){
        return ErrorResponse.builder()
                .message(e.toString())
                .errorCode(INTERNAL_SERVER_ERROR)
                .build();
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ErrorResponse badCredentialException(BadCredentialsException e) {
//        return ErrorResponse.builder()
//                .errorCode(INVALID_CREDENTIALS)
//                .message("존재하지 않는 회원입니다.")
//                .build();
//    }

    @ExceptionHandler(IllegalStateException.class)
    public ErrorResponse IllegalStateException(IllegalStateException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(USER_ILLEGAL_STATE)
                .build();
    }
}
