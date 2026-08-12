package com.project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RESOURCE_NOT_FOUND_EXCEPTION extends RuntimeException {

    public RESOURCE_NOT_FOUND_EXCEPTION(String message){
        super(message);
    }
     public RESOURCE_NOT_FOUND_EXCEPTION(String resourceName,String fieldName,String fieldValue){ 
        this(String.format("%s not found exception: fieldName: %s , fieldValue: %s ",resourceName,fieldName,fieldValue));
     }

}
