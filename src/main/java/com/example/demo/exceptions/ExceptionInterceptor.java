package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public final ResponseEntity<Object> handleAllExceptions(GeneralException ex) {
        GeneralExceptionSchema exceptionResponse =
                new GeneralExceptionSchema(ex.getErrorCode(), ex.getErrorMessage(), ex.getReferenceNumber());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
