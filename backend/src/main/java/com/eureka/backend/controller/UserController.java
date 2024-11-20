package com.eureka.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.backend.model.UserEntity;
import com.eureka.backend.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {
        Optional<UserEntity> userOptional = this.userService.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/count")
    public ResponseEntity<List<Object[]>> getUserCountZone() {
        List<Object[]> counts = this.userService.getUserCountZone();
        return ResponseEntity.ok(counts);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserEntity userEntity) {
        return ResponseEntity.ok(this.userService.save(userEntity));
    }

}
