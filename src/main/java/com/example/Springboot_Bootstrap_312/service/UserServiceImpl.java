package com.example.Springboot_Bootstrap_312.service;

import com.example.Springboot_Bootstrap_312.model.Role;
import com.example.Springboot_Bootstrap_312.model.User;
import com.example.Springboot_Bootstrap_312.repository.RoleRepository;
import com.example.Springboot_Bootstrap_312.repository.UserRepository;
import com.example.Springboot_Bootstrap_312.security.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordConfig passwordConfig;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordConfig passwordConfig) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordConfig = passwordConfig;
    }

    @Override
    public void saveUser(User user) {
        Set<Role> roleSet = new HashSet<>();
        for (Role role : user.getRoles()) {
            roleSet.add(roleRepository.getRoleByRoleName(role.getRoleName()));
        }
        user.setRoles(roleSet);

        if (userRepository.getUserByUsername(user.getUsername()) == null) {
            user.setPassword(passwordConfig.getPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public void updateUser(Long id, User user) {
        Set<Role> roleSet = new HashSet<>();
        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                roleSet.add(roleRepository.getRoleByRoleName(role.getRoleName()));
            }
        } else {
            for (Role existingRole : userRepository.getById(id).getRoles()) {
                roleSet.add(existingRole);
            }
        }
        user.setRoles(roleSet);

        String oldPassword = user.getPassword();
        try {
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(oldPassword);
            } else {
                passwordConfig.getPasswordEncoder().encode(user.getPassword());
            }
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            user.setPassword(oldPassword);
        }
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username);
    }
}