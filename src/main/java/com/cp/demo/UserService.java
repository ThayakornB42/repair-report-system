package com.cp.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public List<User> getAlluser() {
        return (List<User>) userRepository.findAll();
    }
    
    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void deleteUserById(Integer id) {
    	userRepository.deleteById(id);
    }

}
