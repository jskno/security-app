package com.jskno.service;

import com.jskno.exceptions.UsernameAlreadyExistsException;
import com.jskno.persistence.User;
import com.jskno.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            return userRepository.save(user);
        } catch (Exception ex) {
            throw new UsernameAlreadyExistsException("Username '" + user.getUsername() + "' already exists");
        }
    }
}
