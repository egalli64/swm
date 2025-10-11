/*
 * Spring Boot Docker Microservices - User Service
 * 
 * https://github.com/egalli64/dockerized-microservices
 */
package com.example.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService svc;

    public UserController(UserService svc) {
        this.svc = svc;
    }

    /**
     * <pre>
        curl http://localhost:8080/api/users
     * </pre> 
     */
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(svc.getAll());
    }

    /**
     * <pre>
        curl http://localhost:8080/api/users/1
     * </pre> 
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return svc.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * <pre>
        curl http://localhost:8080/api/users/email/tom@example.com
     * </pre> 
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        return svc.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * <pre>
        curl -i -X POST http://localhost:8080/api/users -H "Content-Type: application/json" ^
        -d "{\"name\":\"Tom Li\",\"email\":\"tom@example.com\"}"
     * </pre>
     */
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        try {
            User created = svc.create(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * <pre>
        curl -i -X PUT http://localhost:8080/api/users/1 -H "Content-Type: application/json" ^
        -d "{\"name\":\"Tom Lee\",\"email\":\"tom@example.com\"}"
     * </pre>
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
        try {
            User updated = svc.update(id, user);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * <pre>
        curl -i -X DELETE http://localhost:8080/api/users/1
     * </pre> 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            svc.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
