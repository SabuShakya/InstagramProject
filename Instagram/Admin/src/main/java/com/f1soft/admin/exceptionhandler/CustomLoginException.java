package com.f1soft.admin.exceptionhandler;

public class CustomLoginException extends Exception{
    static String verifyLogin(boolean login){
        if (login == false){
            return "Username or password is incorrect";
        }
        return "Login";
    }
}
