package com.jorgeechavarria.stationery.management_stationery.services.Roles;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jorgeechavarria.stationery.management_stationery.exceptions.RolNotFoundException;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoRoles.RolResponse;
import com.jorgeechavarria.stationery.management_stationery.models.entities.Rol;
import com.jorgeechavarria.stationery.management_stationery.models.mappers.RolMapper;
import com.jorgeechavarria.stationery.management_stationery.repository.roles.RolRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RolServiceImpl implements RolService {

    // Inyección
    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    public RolServiceImpl(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }

    // Obtener todos los roles
    @Override
    public List<RolResponse> getAll() {
        List<Rol> roles = rolRepository.findAll();
        
        if (roles.isEmpty()) {
            return List.of();
        }

        return roles.stream()
                .map(rolMapper::toResponse)
                .toList();
    }

    @Override
    public RolResponse getById(Integer id) {
        Rol existingRol = rolRepository.findById(id)
                .orElseThrow(() -> new RolNotFoundException("No se encontró un rol con ID: " + id));

        return rolMapper.toResponse(existingRol);
    }
}
