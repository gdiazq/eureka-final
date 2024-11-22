package com.eureka.backend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import com.eureka.backend.model.UserEntity;
import com.eureka.backend.model.ZoneEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private ZoneEntity zone1;
    private ZoneEntity zone2;
    private ZoneEntity zone3;
    private ZoneEntity zone4;
    private ZoneEntity zone5;

    @BeforeEach
    public void setUp() {
        zone1 = new ZoneEntity(1L, "Front Developer", null);
        zone2 = new ZoneEntity(2L, "Backend Developer", null);
        zone3 = new ZoneEntity(3L, "UX/UI", null);
        zone4 = new ZoneEntity(4L, "Disenador Grafico", null);
        zone5 = new ZoneEntity(5L, "Project Manager", null);

        UserEntity user1 = new UserEntity(1L, "Juan Pérez", "juan.perez@example.com", zone1);
        UserEntity user2 = new UserEntity(2L, "Ana Gómez", "ana.gomez@example.com", zone2);
        UserEntity user3 = new UserEntity(3L, "Luis Martínez", "luis.martinez@example.com", zone3);
        UserEntity user4 = new UserEntity(4L, "Carlos Sánchez", "carlos.sanchez@example.com", zone4);
        UserEntity user5 = new UserEntity(5L, "María López", "maria.lopez", zone5);

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
        assertThat(userRepository.findAll()).hasSize(5);
    }

    @Test
    public void testGetUserCountZone() {
        List<Object[]> results = userRepository.getUserCountZone();
        assertThat(results).hasSize(5);
        assertThat(results).extracting(objects -> objects[0]).containsExactlyInAnyOrder("Front Developer", "Backend Developer", "UX/UI", "Disenador Grafico", "Project Manager");
        assertThat(results).extracting(objects -> objects[1]).containsExactlyInAnyOrder(1L, 1L, 1L, 1L, 1L);
        
    }
}