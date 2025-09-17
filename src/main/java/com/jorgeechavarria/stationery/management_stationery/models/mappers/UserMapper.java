package com.jorgeechavarria.stationery.management_stationery.models.mappers;
import org.springframework.stereotype.Component;

import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoUsers.UserRequest;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoUsers.UserResponse;
import com.jorgeechavarria.stationery.management_stationery.models.entities.Rol;
import com.jorgeechavarria.stationery.management_stationery.models.entities.User;

@Component // Esta clase es un bean, crea y se guarda en contenedor "OBJETO"
public class UserMapper {

    public UserResponse toResponse(User user){
        var response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getUserName());
        response.setEmail(user.getEmail());
        response.setRol(user.getRol().getRolName());
        return response;
    }

    public User toEntity(UserRequest userRequest){
        var user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        // Asociar el rol usando solo el id recibido en el request
        var rol = new Rol();
        rol.setId(userRequest.getIdRol());
        user.setRol(rol);

        return user;
    }

}
