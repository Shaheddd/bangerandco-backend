package com.example.bangerandco.repository;

import com.example.bangerandco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<User, Integer> {
}
