package com.example.Springboot_Bootstrap_312.service;

import com.example.Springboot_Bootstrap_312.model.Role;
import com.example.Springboot_Bootstrap_312.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRole(Role role) {
        if (roleRepository.getRoleByRoleName(role.getRoleName()) == null) {
            roleRepository.save(role);
        }
    }

    @Override
    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.getRoleByRoleName(roleName);
    }
}