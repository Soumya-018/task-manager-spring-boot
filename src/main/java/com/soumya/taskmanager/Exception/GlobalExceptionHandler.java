package com.soumya.taskmanager.Exception;

import com.soumya.taskmanager.Payload.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiError> handleTaskNotFound(TaskNotFoundException ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("message",ex.getMessage());

        ApiError apiError = new ApiError(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),"task not found",errors);

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex){

        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errors.put(error.getField(),error.getDefaultMessage());

        });

        ApiError apiError = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),"Validation_falied",errors);
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
}
