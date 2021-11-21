package com.example.Springboot_Bootstrap_312.repository;

import com.example.Springboot_Bootstrap_312.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsername(String username);
}