package com.jorgeechavarria.stationery.management_stationery.repository.users;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorgeechavarria.stationery.management_stationery.models.entities.User;


public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String email);

}
