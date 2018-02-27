package com.users.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainExceptionHandler {


    @ExceptionHandler(NoFollowingException.class)
    public ResponseEntity<ExceptionJSON> noFollowingException(NoFollowingException e){
//        NoFollowingException exception = new NoFollowingException("Follow others to see their Posts","Not following anyone");
        return new ResponseEntity<ExceptionJSON>(e.getExceptionJSON(),e.getExceptionJSON().getStatus());
    }
}
