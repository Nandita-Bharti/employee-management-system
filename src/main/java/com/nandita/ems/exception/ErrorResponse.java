package com.nandita.ems.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private int status;

    private String error;

    private String message;

    private LocalDateTime timestamp;

    private String path;
}