package com.backend.app.posts.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String s) {
        super(s);
    }
}
