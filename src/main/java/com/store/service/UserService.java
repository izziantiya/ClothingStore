package com.store.service;

import com.store.entity.User;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
