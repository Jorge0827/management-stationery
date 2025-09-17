package com.jorgeechavarria.stationery.management_stationery.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoRoles.RolResponse;
import com.jorgeechavarria.stationery.management_stationery.services.Roles.RolService;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<RolResponse>> getAllRoles() {
        List<RolResponse> roles = rolService.getAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolResponse> getById(@PathVariable Integer id) {
        RolResponse rol = rolService.getById(id);
        return ResponseEntity.ok(rol);
    }

}
