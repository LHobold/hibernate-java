package com.example.hibernatejava.exceptions;

public class WrongIndexException extends Exception {

    public WrongIndexException(String message) {
        super(message);
    }

    public WrongIndexException() {
        super();
    }

}
