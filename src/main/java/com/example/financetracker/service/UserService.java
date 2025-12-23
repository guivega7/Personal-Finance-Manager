package com.example.financetracker.service;

import com.example.financetracker.model.User;
import com.example.financetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User saveUser(User user){
        String plainPassword = user.getPassword();

        String encodedPassword = encoder.encode(plainPassword);

        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
}
