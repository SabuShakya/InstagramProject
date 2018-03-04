package com.users.exceptionHandler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NoFollowingException extends RuntimeException{
    private ExceptionJSON exceptionJSON;

    public NoFollowingException(String message,String developerMessage){
        super(message);
        exceptionJSON= new ExceptionJSON();
        exceptionJSON.setMessage(message);
        exceptionJSON.setDeveloperMessage(developerMessage);
//        exceptionJSON.setStatus(HttpStatus.NOT_FOUND);
    }


}
