package com.blogapp.com.postservice.exceptions;


public class PostNotFoundException extends Exception{
    public PostNotFoundException(String message) {
        super(message);
    }
}
