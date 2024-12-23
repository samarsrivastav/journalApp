package com.journalApp.journalApp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.journalApp.journalApp.entity.User;
import com.journalApp.journalApp.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        return user;
    }
    public User createAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
        return user;
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public boolean deleteByUsername(String username){
        User userInDb= userRepository.findByUsername(username);
        userRepository.delete(userInDb);
        return true;
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }

}
