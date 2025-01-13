package com.learning.accounts.controller;

import com.learning.accounts.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, Exception exception){

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorTime(LocalDateTime.now());
        errorResponse.setErrMsg(exception.getMessage());
        errorResponse.setApiPath(request.getRequestURI());
        errorResponse.setMethod(request.getMethod());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, MethodArgumentNotValidException exception){
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setErrorTime(LocalDateTime.now());
        errorResponse.setErrMsg(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        errorResponse.setApiPath(request.getRequestURI());
        errorResponse.setMethod(request.getMethod());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);

    }
}
