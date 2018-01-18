package com.f1soft.admin.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.login.LoginException;

@ControllerAdvice
public class AdminExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = LoginException.class)
    public ResponseEntity<String> handleLoginException(){
        String bodyOfResponse = "Username or password is incorrect";
        return new ResponseEntity<String>(bodyOfResponse,HttpStatus.OK);
    }
}
