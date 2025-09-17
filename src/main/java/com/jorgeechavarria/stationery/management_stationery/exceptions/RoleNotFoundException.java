package com.jorgeechavarria.stationery.management_stationery.exceptions;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(Integer id){
        super("El rol con id " + id + " no fue encontrado.");
    }

}
