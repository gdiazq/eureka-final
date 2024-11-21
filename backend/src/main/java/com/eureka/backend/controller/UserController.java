package com.eureka.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.backend.dto.UserDto;
import com.eureka.backend.model.UserEntity;
import com.eureka.backend.model.ZoneEntity;
import com.eureka.backend.service.UserService;
import com.eureka.backend.service.ZoneService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ZoneService zoneService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {
        Optional<UserEntity> userOptional = this.userService.findById(id);
        return userOptional.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public ResponseEntity<List<Object[]>> getUserCountZone() {
        List<Object[]> counts = this.userService.getUserCountZone();
        return ResponseEntity.ok(counts);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        Optional<ZoneEntity> zoneOptional =  zoneService.findById(userDto.getZonaId());
        if (!zoneOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Zone not found");
        }
        UserEntity userEntity = UserEntity.builder()
                .nombre(userDto.getNombre())
                .email(userDto.getEmail())
                .zona(zoneOptional.get())
                .build();
        UserEntity createdUser = userService.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
