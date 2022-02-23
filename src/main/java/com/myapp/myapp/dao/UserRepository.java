package com.myapp.myapp.dao;

import java.util.Optional;

import com.myapp.myapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
