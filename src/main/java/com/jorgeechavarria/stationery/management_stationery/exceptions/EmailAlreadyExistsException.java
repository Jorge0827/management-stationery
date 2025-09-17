package com.jorgeechavarria.stationery.management_stationery.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String email){
        super("El email " + email + " ya está registrado");
    }

}
