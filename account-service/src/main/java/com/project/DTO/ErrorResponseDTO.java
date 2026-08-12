package com.project.DTO;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponseDTO<T> {

    private String apiPath; // endpoint error 
    private HttpStatus errorCode; // 401 404 403 
    private T errorMessage; // error message
    private LocalDateTime errorTime; // errortime 

}
