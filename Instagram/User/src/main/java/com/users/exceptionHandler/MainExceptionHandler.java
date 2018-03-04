package com.users.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class MainExceptionHandler {


    @ExceptionHandler(NoFollowingException.class)
    public ResponseEntity<ExceptionJSON> noFollowingException(NoFollowingException e){
//        NoFollowingException exception = new NoFollowingException("Follow others to see their Posts","Not following anyone");
        return new ResponseEntity<ExceptionJSON>(e.getExceptionJSON(),HttpStatus.NOT_FOUND);
    }

   @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ExceptionJSON> incorrectPasswordOrUserNotRegisteredException(IncorrectPasswordException e){
        return new ResponseEntity<ExceptionJSON>(e.getExceptionJSON(),HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ExceptionJSON> sqlException(NullPointerException e){
//        ExceptionJSON exceptionJSON = new ExceptionJSON(e.getMessage(), "Null pointer occurred.");
//        return new ResponseEntity<ExceptionJSON>(exceptionJSON,HttpStatus.NOT_FOUND);
//    }
}
