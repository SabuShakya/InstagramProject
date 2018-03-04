package com.users.exceptionHandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncorrectPasswordException extends RuntimeException {
    private ExceptionJSON exceptionJSON;

    public IncorrectPasswordException(String errorMsg,String developerMsg) {
        super(errorMsg);
        exceptionJSON = new ExceptionJSON();
        exceptionJSON.setMessage(errorMsg);
        exceptionJSON.setDeveloperMessage(developerMsg);
    }
}
