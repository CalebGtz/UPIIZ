package com.example.restaurant.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { super(message); }
}
