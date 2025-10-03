package com.jorgeechavarria.stationery.management_stationery.services.users;

import java.util.List;

import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoUsers.UserRequest;
import com.jorgeechavarria.stationery.management_stationery.models.dtos.dtoUsers.UserResponse;

public interface UserService {

    List <UserResponse> getAll();

    UserResponse create(UserRequest userRequest);

    UserResponse update(Integer id, UserRequest userRequest);

    UserResponse delete(Integer id);

}
