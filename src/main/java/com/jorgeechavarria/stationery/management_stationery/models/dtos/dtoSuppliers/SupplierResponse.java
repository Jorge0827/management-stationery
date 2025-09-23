package com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoSuppliers;

import lombok.Data;

@Data
public class SupplierResponse {

    private Integer id;
    private String name;
    private String prefix;
    private String phoneNumber;
    private String addres;
    private String email;
    private String nit;

}
