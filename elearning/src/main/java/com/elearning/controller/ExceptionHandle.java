package com.elearning.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.elearning.exception.CustomException;
import com.elearning.exception.ExceptionResponse;

@ControllerAdvice
public class ExceptionHandle {
    
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(CustomException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        exceptionResponse.setMessage(exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(NoResourceFoundException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        exceptionResponse.setMessage(e.getLocalizedMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        exceptionResponse.setMessage(e.getLocalizedMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
