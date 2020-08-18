package com.example.bangerandco.repository;

import com.example.bangerandco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer> {

}
