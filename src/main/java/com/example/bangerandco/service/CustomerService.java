package com.example.bangerandco.service;

import com.example.bangerandco.model.User;
import com.example.bangerandco.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public User saveUser(User user) { return  customerRepository.save(user); }

    public List<User> getUsers() { return customerRepository.findAll(); }
}
