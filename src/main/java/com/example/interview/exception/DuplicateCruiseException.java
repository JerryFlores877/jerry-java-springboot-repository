package com.example.interview.exception;

public class DuplicateCruiseException extends RuntimeException {
    public DuplicateCruiseException(String message) {
        super(message);
    }
}