package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository repo;

    // Register new user
    public User savingNewUser(User user) {
        return repo.save(user);
    }

    // Login with Email + Password + Role
    public User authEmailPasswordAndRole(User user) {

        User user2 = repo.findByEmailAndPasswordAndRole(
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );

        if (user2 == null) {
            throw new RuntimeException("Invalid Email, Password or Role");
        }

        return user2;
    }
}