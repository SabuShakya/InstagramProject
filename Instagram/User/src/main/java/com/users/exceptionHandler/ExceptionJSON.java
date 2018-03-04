package com.users.exceptionHandler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionJSON {
    private String message;
    private String developerMessage;

    public ExceptionJSON(){

    }
    public ExceptionJSON(String message,String developerMessage){
        this.message = message;
        this.developerMessage = developerMessage;
    }
}
