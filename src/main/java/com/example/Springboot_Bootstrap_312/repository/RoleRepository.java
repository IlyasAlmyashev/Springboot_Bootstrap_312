package com.example.Springboot_Bootstrap_312.repository;

import com.example.Springboot_Bootstrap_312.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByRoleName(String roleName);
}