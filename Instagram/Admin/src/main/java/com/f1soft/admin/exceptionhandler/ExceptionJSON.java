package com.f1soft.admin.exceptionhandler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionJSON {
    private String errorMsg;
    private String developerMessage;

    public ExceptionJSON(){}

    public ExceptionJSON(String errorMsg, String developerMessage) {
        this.errorMsg = errorMsg;
        this.developerMessage = developerMessage;
    }
}
