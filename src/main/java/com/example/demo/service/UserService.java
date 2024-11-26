package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUserByString(String name) {
        return userRepository.findByNameOrAll(name);
    }

    public User createUser(User user) {
        Optional<User> checkMail = userRepository.findByEmail(user.getEmail());
        if (checkMail.isPresent()) {
            throw new RuntimeException("Email đã được sử dụng.");
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        Optional<User> checkMail = userRepository.findByEmail(userDetails.getEmail());
        if (checkMail.isPresent() && !checkMail.get().getId().equals(id)) {
            throw new RuntimeException("Email đã được sử dụng.");
        }
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
