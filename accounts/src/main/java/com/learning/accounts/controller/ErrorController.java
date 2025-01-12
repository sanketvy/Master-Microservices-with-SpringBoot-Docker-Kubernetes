package com.learning.accounts.controller;

import com.learning.accounts.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, Exception exception){

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorTime(LocalDateTime.now());
        errorResponse.setErrMsg(exception.getMessage());
        errorResponse.setApiPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
