package com.kameleoon.quoteservice.controller;

import com.kameleoon.quoteservice.exception.ChangeExistingVoteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ChangeExistingVoteException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleEntityNotFoundEx(HttpStatus httpStatus) {
        return new ResponseEntity<>(httpStatus);
    }
}
