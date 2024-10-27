package com.project.dalda.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse<T> {

    private static final String SUCCESS_STATUS = "SUCCESS";
    private static final String ERROR_STATUS = "ERROR";

    private String status;
    private String message;
    private T data;

    public static <T> CommonResponse<T> ok(T data, String message) {
        return CommonResponse.<T>builder()
                .data(data)
                .status(SUCCESS_STATUS)
                .message(message)
                .build();
    }

    public static <T> CommonResponse<T> error(String errorMessage) {
        return CommonResponse.<T>builder()
                .status(ERROR_STATUS)
                .message(errorMessage)
                .build();
    }

}
