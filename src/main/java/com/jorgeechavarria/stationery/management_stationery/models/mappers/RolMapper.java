package com.jorgeechavarria.stationery.management_stationery.models.mappers;

import org.springframework.stereotype.Component;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoRoles.RolResponse;
import com.jorgeechavarria.stationery.management_stationery.models.entities.Rol;

@Component // Esta clase es un bean, crea y se guarda en contenedor "OBJETO"
public class RolMapper {

    public RolResponse toResponse(Rol rol) {
        var response = new RolResponse();
        response.setId(rol.getId());
        response.setRolName(rol.getRolName());
        return response;
    }

}
