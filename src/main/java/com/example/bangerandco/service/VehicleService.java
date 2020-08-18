package com.example.bangerandco.service;

import com.example.bangerandco.model.Vehicle;
import com.example.bangerandco.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public List<Vehicle> getVehicles()
    {
        return repository.findAll();
    }

    public Vehicle saveVehicle(Vehicle vehicle) {

        return repository.save(vehicle);
    }

    public List<Vehicle> saveVehicles(List<Vehicle> vehicles) {

        return repository.saveAll(vehicles);
    }

    public String deleteVehicle(int id) {

        repository.deleteById(id);
        return "Vehicle ID : " +id+ " Has Been Removed Successfully";
    }

    public Vehicle updateVehicle (Vehicle vehicle) {

        Vehicle existingVehicle = repository.findById(vehicle.getId()).orElse(null);
        existingVehicle.setVehicleName(vehicle.getVehicleName());
        existingVehicle.setCategory(vehicle.getCategory());
        existingVehicle.setTransmissionType(vehicle.getTransmissionType());
        existingVehicle.setFuelType(vehicle.getFuelType());

        return repository.save(existingVehicle);
    }

}
