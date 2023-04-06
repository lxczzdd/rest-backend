package com.backend.app.auth.exception;

public class AuthAlreadyExistException extends RuntimeException {
    public AuthAlreadyExistException(String s) {
        super(s);
    }
}