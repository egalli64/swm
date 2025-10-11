/*
 * Spring Boot Docker Microservices - User Service
 * 
 * https://github.com/egalli64/dockerized-microservices
 */
package com.example.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);
}
