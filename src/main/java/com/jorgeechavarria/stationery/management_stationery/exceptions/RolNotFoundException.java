package com.jorgeechavarria.stationery.management_stationery.exceptions;

public class RolNotFoundException extends RuntimeException {
    
    public RolNotFoundException(String message) {
        super(message);
    }
    
    public RolNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
