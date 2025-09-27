package com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoProducts;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductResponse {

    private String id;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private Integer CurrentStock;


}
