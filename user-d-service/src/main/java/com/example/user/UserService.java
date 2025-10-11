/*
 * Spring Boot Docker Microservices - User Service
 * 
 * https://github.com/egalli64/dockerized-microservices
 */
package com.example.user;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository repo;
    private final RabbitTemplate rabbit;

    public UserService(UserRepository repo, RabbitTemplate rabbit) {
        this.repo = repo;
        this.rabbit = rabbit;
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public Optional<User> getById(Long id) {
        return repo.findById(id);
    }

    public Optional<User> getByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Transactional
    public User create(User user) {
        if (repo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User " + user.getEmail() + " already exists");
        }

        User savedUser = repo.save(user);
        rabbit.convertAndSend("user.exchange", "user.created", savedUser.getId());

        return savedUser;
    }

    @Transactional
    public User update(Long id, User userDetails) {
        User user = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found: " + id));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());

        return repo.save(user);
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("User not found with id: " + id);
        } else {
            repo.deleteById(id);
            rabbit.convertAndSend("user.exchange", "user.deleted", id);
        }
    }
}
