package com.project.dalda.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String status;
    private String message;
    private String errorCode;
}