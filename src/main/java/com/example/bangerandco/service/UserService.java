package com.example.bangerandco.service;


import com.example.bangerandco.model.User;
import com.example.bangerandco.model.UserLogin;
import com.example.bangerandco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserLogin saveUser(UserLogin user) { return repository.save(user);}

    public List<UserLogin> getUsers() {return repository.findAll();}
}
