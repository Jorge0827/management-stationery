package com.jorgeechavarria.stationery.management_stationery.services.users;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jorgeechavarria.stationery.management_stationery.exceptions.EmailAlreadyExistsException;
import com.jorgeechavarria.stationery.management_stationery.exceptions.RoleNotFoundException;
import com.jorgeechavarria.stationery.management_stationery.exceptions.UserNotFoundException;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoUsers.UserRequest;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoUsers.UserResponse;
import com.jorgeechavarria.stationery.management_stationery.models.mappers.UserMapper;
import com.jorgeechavarria.stationery.management_stationery.repository.users.UserRepository;
import com.jorgeechavarria.stationery.management_stationery.repository.roles.RolRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    // Inyeccion de dependencias
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RolRepository rolRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.userMapper = userMapper;
    }

    // Logica pra mostrar todos los usuarios
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        log.debug("Iniciando consulta de usuarios a la BD");
        var users = userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
        return users;

    }

    // Logica para crear un nuevo usuario
    @Override
    @Transactional
    public UserResponse create(UserRequest userRequest) {
        log.debug("Payload recibido: idRol={}", userRequest.getIdRol());


        //verficar existencia de rol
        var rol = rolRepository.findById(userRequest.getIdRol())
                .orElseThrow(() -> new RoleNotFoundException(userRequest.getIdRol()));

        //Verificar existencia de email
        var existingEmail = userRepository.findByEmail(userRequest.getEmail());
        if (existingEmail.isPresent()) {
            throw new EmailAlreadyExistsException(userRequest.getEmail());
        }

        var newEntity = userMapper.toEntity(userRequest);
        newEntity.setRol(rol);

        newEntity = userRepository.save(newEntity);
        log.info("Creación de usuario exitosa: id={}", newEntity.getId());
        return userMapper.toResponse(newEntity);
    }

    // Logica pra modificar un usuario siempre y cuando exista su id y exista el rol
    @Override
    @Transactional
    public UserResponse update(Integer id, UserRequest userRequest) {

        log.debug("Recibiendo id {} para actualizar", id);
        var existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        var rol = rolRepository.findById(userRequest.getIdRol())
                .orElseThrow(() -> new RoleNotFoundException(userRequest.getIdRol()));

        existingUser.setUserName(userRequest.getUserName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPassword(userRequest.getPassword());
        existingUser.setRol(rol);

        var updateUser = userRepository.save(existingUser);
        log.info("Modificación al usuario con id {} exitosa", updateUser.getId());
        return userMapper.toResponse(updateUser);
    }

    // Logica para eliminar un usuario
    @Transactional
    @Override
    public UserResponse delete(Integer id) {
        log.debug("Recibiendo usuario con id {} para eliminar", id);
        var existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(existingUser);
        log.info("Usuario con id {} eliminado exitosamente.", id);
        return userMapper.toResponse(existingUser);

    }

}
