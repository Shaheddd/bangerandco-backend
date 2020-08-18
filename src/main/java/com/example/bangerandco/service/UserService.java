package com.example.bangerandco.service;


import com.example.bangerandco.model.User;
import com.example.bangerandco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user) { return repository.save(user);}

    public List<User> getUsers() {return repository.findAll();}
}
