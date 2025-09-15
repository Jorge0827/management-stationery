package com.jorgeechavarria.stationery.management_stationery.services.Roles;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jorgeechavarria.stationery.management_stationery.models.dtos.DtoRoles.RolResponse;
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
        log.debug("Inicio de consulta de todos los roles");
        List<Rol> roles = rolRepository.findAll();
        log.debug("Se encontraron {} roles en BD", roles.size());

        List<RolResponse> response = roles.stream()
                .map(rolMapper::toResponse).toList();

        log.info("Consulta con exito: {} roles devueltos", response.size());
        return response;
    }

    @Override
    public RolResponse getById(Integer id) {
        log.debug("Buscando rol con ID: {}", id);
        var existingRol = rolRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Rol con ID {} no fue encontrado", id);
                    return new RuntimeException("Rol no encontrado");

                });

        RolResponse response = rolMapper.toResponse(existingRol);
        log.info("Rol encontrado: {} (ID: {})", response.getRolName(), id);
        return response;

    }
}
