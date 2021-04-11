package com.numbersector.controller;

import com.numbersector.exception.NumberNotValidException;
import com.numbersector.exception.SectorRequestException;
import com.numbersector.model.SectorErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SectorExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<SectorErrorResponse> handleNumberNotValidException(NumberNotValidException ex) {
        SectorErrorResponse error = new SectorErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SectorErrorResponse> handleSectorRequestException(SectorRequestException ex) {
        SectorErrorResponse error = new SectorErrorResponse();
        error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
