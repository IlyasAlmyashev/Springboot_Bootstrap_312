package com.example.Springboot_Bootstrap_312.service;
import com.example.Springboot_Bootstrap_312.model.Role;
import com.example.Springboot_Bootstrap_312.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    void saveUser(User user);

    void updateUser(Long id, User user);

    void deleteById(Long id);

    User findById(Long id);

    List<User> findAll();
}