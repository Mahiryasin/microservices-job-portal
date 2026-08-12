package com.project.Exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.DTO.ErrorResponseDTO;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice // responseBody+controlleradvice !
public class Global_Exception_Handler extends ResponseEntityExceptionHandler {

      // computeıfabsent yerıne !
    // private List<String> turns(List<String>list,String message){
    //     list.add(message);
    //     return list;
    // }

   @Override
   protected  ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    Map<String, List<String>> errorsMap = new HashMap<>();

    for(ObjectError error : ex.getBindingResult().getAllErrors()){
        String message = error.getDefaultMessage();
        String field = ((FieldError) error).getField();

        errorsMap.computeIfAbsent(field,(k)->new ArrayList<>()).add(message);
}

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse(request.getContextPath(),errorsMap,HttpStatus.BAD_REQUEST));
   }
   @ExceptionHandler(value = ConstraintViolationException.class)
   public ResponseEntity<ErrorResponseDTO<Map<String, List<String>>>> ConstraintViolationException(ConstraintViolationException ex,HttpServletRequest request){
      Map<String, List<String>> errorsMap=new HashMap<>();
      
      for(ConstraintViolation<?> violation : ex.getConstraintViolations()){
          String errorMessage=violation.getMessage();
          String field=violation.getPropertyPath().toString();

          errorsMap.computeIfAbsent(field, (key)->new ArrayList<>()).add(errorMessage);

      }
     
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse(request.getContextPath(), errorsMap, HttpStatus.BAD_REQUEST));
   }
    
   

    // other errors ! not handled by our custome error 
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponseDTO> Exception_Handler(Exception ex,HttpServletRequest request){
       
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(request.getContextPath(),ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR));

    }

    @ExceptionHandler(value = ALREADY_EXIST_MOBILE_NUMBER.class)
    public ResponseEntity<ErrorResponseDTO<String>> ALREADY_EXIST_MOBILE_NUMBER_Handler(ALREADY_EXIST_MOBILE_NUMBER ex,HttpServletRequest request){
       
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse(request.getContextPath(),ex.getMessage(),HttpStatus.BAD_REQUEST));
    }

    
    @ExceptionHandler(value = RESOURCE_NOT_FOUND_EXCEPTION.class)
    public ResponseEntity<ErrorResponseDTO<String>> RESOURSE_NOT_FOUND_EXCEPTION(RESOURCE_NOT_FOUND_EXCEPTION ex,HttpServletRequest request){
       
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildErrorResponse(request.getContextPath(),ex.getMessage(),HttpStatus.NOT_FOUND));
    }

    private <T> ErrorResponseDTO<T> buildErrorResponse(String apiPath,T data,HttpStatus status){
        ErrorResponseDTO<T> errorResponseDTO=new ErrorResponseDTO<>();
        errorResponseDTO.setApiPath(apiPath);
        errorResponseDTO.setErrorTime(LocalDateTime.now());
        errorResponseDTO.setErrorMessage(data);
        errorResponseDTO.setErrorCode(status);
        return errorResponseDTO;
    }

}
