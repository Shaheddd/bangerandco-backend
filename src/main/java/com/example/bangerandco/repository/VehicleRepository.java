package com.example.bangerandco.repository;

import com.example.bangerandco.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository <Vehicle, Integer> {

}
