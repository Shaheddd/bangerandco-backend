package com.example.bangerandco;

import com.example.bangerandco.model.User;
import com.example.bangerandco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BangerandcoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BangerandcoApplication.class, args);
    }

//    @Autowired
//    private UserRepository repository;
//
//    @Override
//    public void run(String[] args) throws Exception {
//        this.repository.save(new User("Shahed Saldin", "93328456V", "Admin", "shahed.saldin@gmail.com", "23-11-1993"));
//    }
}
