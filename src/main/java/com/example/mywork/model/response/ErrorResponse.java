package com.example.mywork.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponse<T> {
    private String status;
    private String message;
    private T data;
}
