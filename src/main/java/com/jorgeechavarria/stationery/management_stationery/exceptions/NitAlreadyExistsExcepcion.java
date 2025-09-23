package com.jorgeechavarria.stationery.management_stationery.exceptions;

public class NitAlreadyExistsExcepcion extends RuntimeException{

    public NitAlreadyExistsExcepcion(String nit){
        super("El nit" + nit + "ya se encuentra registrado");
    }

}
