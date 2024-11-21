package com.eureka.backend.controller;

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

import com.eureka.backend.model.ZoneEntity;
import com.eureka.backend.service.ZoneService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/zones")
@AllArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping
    public Iterable<?> findAll() {
        return this.zoneService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {
        Optional<ZoneEntity> zoneOptional = this.zoneService.findById(id);
        if (zoneOptional.isPresent()) {
            return ResponseEntity.ok(zoneOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ZoneEntity zoneEntity) {
        ZoneEntity createdZone = this.zoneService.save(zoneEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdZone);
    }


}
