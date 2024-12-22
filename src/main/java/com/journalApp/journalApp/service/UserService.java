package com.journalApp.journalApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.journalApp.journalApp.entity.User;
import com.journalApp.journalApp.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User createUser(User user){
        userRepository.save(user);
        return user;
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
