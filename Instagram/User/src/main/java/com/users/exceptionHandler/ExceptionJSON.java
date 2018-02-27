package com.users.exceptionHandler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionJSON {
    private String message;
    private HttpStatus status;
    private String developerMessage;

    public ExceptionJSON(){

    }
    public ExceptionJSON(String message,HttpStatus status,String developerMessage){
        this.message = message;
        this.status = status;
        this.developerMessage = developerMessage;
    }
}
