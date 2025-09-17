package com.jorgeechavarria.stationery.management_stationery.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Integer id){
        super("EL usuario con id: " + id + " no fue encontrado");
    }

    public UserNotFoundException(String email){
        super("EL usuario con email: " + email + "no fue encontrado");
    }

}
