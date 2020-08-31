package com.example.bangerandco.repository;

import java.util.Optional;
import com.example.bangerandco.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserLogin, Integer> {

    Optional<UserLogin> findByUsername (String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
