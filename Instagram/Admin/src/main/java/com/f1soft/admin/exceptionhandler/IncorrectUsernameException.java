package com.f1soft.admin.exceptionhandler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class IncorrectUsernameException extends RuntimeException {
    private ExceptionJSON exceptionJSON;

    public IncorrectUsernameException(String errorMsg, String developerMessage) {
        super(errorMsg);
        exceptionJSON= new ExceptionJSON();
        exceptionJSON.setErrorMsg(errorMsg);
        exceptionJSON.setDeveloperMessage(developerMessage);
    }
}
