package com.jwt_auth.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt_auth.api.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findUserByUsername(String username);
}
