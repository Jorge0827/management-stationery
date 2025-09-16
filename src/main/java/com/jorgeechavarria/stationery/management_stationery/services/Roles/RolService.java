package com.jorgeechavarria.stationery.management_stationery.services.Roles;

import java.util.List;

import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoRoles.RolResponse;

public interface RolService {

    List<RolResponse> getAll();

    RolResponse getById(Integer id);

}
