package com.blogapp.com.postservice.exceptions;

public class PostNotSavedException extends Exception{
    public PostNotSavedException(String message) {
        super(message);
    }
}
