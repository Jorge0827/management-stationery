package com.jorgeechavarria.stationery.management_stationery.models.mappers;

import org.springframework.stereotype.Component;

import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoSuppliers.SupplierRequest;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoSuppliers.SupplierResponse;
import com.jorgeechavarria.stationery.management_stationery.models.entities.Supplier;

@Component
public class SupplierMapper {

    public SupplierResponse toResponse(Supplier entity){
        var response = new SupplierResponse();

        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setPrefix(entity.getPrefix());
        response.setPhoneNumber(entity.getPhoneNumber());
        response.setAddres(entity.getAddres());
        response.setEmail(entity.getEmail());
        response.setNit(entity.getNit());

        return response;
    }

    public Supplier toEntity(SupplierRequest request){

        var entity = new Supplier();

        entity.setName(request.getName());
        entity.setPrefix(request.getPrefix());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setAddres(request.getAddres());
        entity.setEmail(request.getEmail());
        entity.setNit(request.getNit());

        return entity;

    }

}
