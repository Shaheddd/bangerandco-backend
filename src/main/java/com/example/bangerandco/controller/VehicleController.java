package com.example.bangerandco.controller;

import com.example.bangerandco.model.Vehicle;
import com.example.bangerandco.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class VehicleController {

    @Autowired
    private VehicleService service;

    @PostMapping("/addVehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){return service.saveVehicle(vehicle);}

    @PostMapping("/addMultipleVehicles")
    public List<Vehicle> addMultipleVehicles(@RequestBody List<Vehicle> vehicles) {return service.saveVehicles(vehicles);}

    @GetMapping("/ListAllVehicles")
    public List <Vehicle> findAllVehicles() {return service.getVehicles();}

    @DeleteMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable int id) {return service.deleteVehicle(id);}

    @PutMapping("/update")
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle) {return service.updateVehicle(vehicle); }

}
