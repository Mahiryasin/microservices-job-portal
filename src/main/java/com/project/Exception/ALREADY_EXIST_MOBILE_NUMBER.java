package com.project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ALREADY_EXIST_MOBILE_NUMBER extends RuntimeException {

    public ALREADY_EXIST_MOBILE_NUMBER(String message) {
        super(message);
    }

}
