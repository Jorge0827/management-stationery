package com.jorgeechavarria.stationery.management_stationery.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoRoles.RolResponse;
import com.jorgeechavarria.stationery.management_stationery.services.Roles.RolService;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public List<RolResponse> getAllRoles() {
        log.info("Solicitando todos los roles");
        List<RolResponse> roles = rolService.getAll();
        log.info("Se devolvieron {} roles", roles.size());
        return roles;
    }

    @GetMapping("/{id}")
    public RolResponse getById(@PathVariable Integer id) {
        log.info("Solicitando rol con ID: {}", id);
        var rol = rolService.getById(id);
        return rol;
    }

}
