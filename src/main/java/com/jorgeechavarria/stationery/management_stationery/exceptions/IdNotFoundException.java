package com.jorgeechavarria.stationery.management_stationery.exceptions;

public class IdNotFoundException  extends RuntimeException{

    public IdNotFoundException(Integer id){
        super("El id" + id + "no existe");
    }

}
