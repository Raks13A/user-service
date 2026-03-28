package com.example.UserApplicationProject.UserApplicationInstaller.repository;


import com.example.UserApplicationProject.UserApplicationInstaller.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
