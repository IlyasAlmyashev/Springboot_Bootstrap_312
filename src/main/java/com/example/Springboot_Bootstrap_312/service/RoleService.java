package com.example.Springboot_Bootstrap_312.service;

import com.example.Springboot_Bootstrap_312.model.Role;

import java.util.List;

public interface RoleService {

    void saveRole(Role role);

    void updateRole(Role role);

    void deleteById(Long id);

    Role findById(Long id);

    List<Role> findAll();

    Role getRoleByName(String roleName);
}