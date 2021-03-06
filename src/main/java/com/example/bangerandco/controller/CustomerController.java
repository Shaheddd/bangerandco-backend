package com.example.bangerandco.controller;

import com.example.bangerandco.model.User;
import com.example.bangerandco.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/addUser")
    public User addUser (@RequestBody User user) { return service.saveUser(user); }

    @GetMapping("/listAllUsers")
    public List<User> findAllUsers() { return service.getUsers(); }
}
