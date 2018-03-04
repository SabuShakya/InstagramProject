package com.f1soft.admin.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(IncorrectUsernameException.class)
    public ResponseEntity<ExceptionJSON> incorrectUsername(IncorrectUsernameException e){
        return new ResponseEntity<ExceptionJSON>(e.getExceptionJSON(),HttpStatus.NOT_FOUND);
    }
//
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ExceptionJSON> nullPointerException(NullPointerException e){
//        ExceptionJSON exceptionJSON= new ExceptionJSON(e.getMessage(), "Null pointer occured");
//        return new ResponseEntity<ExceptionJSON>(exceptionJSON, HttpStatus.NOT_FOUND);
//    }
}
