package com.eureka.backend.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @BeforeEach
    public void setUp() {
        zone1 = new ZoneEntity(1L, "Front Developer", null);
        zone2 = new ZoneEntity(2L, "Backend Developer", null);

        UserEntity user1 = new UserEntity(1L, "Juan Pérez", "juan.perez@example.com", zone1);
        UserEntity user2 = new UserEntity(2L, "Ana Gómez", "ana.gomez@example.com", zone1);
        UserEntity user3 = new UserEntity(3L, "Luis Martínez", "luis.martinez@example.com", zone2);

        userRepository.saveAll(Arrays.asList(user1, user2, user3));
        assertThat(userRepository.findAll()).hasSize(3);
    }

    @Test
    public void testGetUserCountZone() {
        List<Object[]> results = userRepository.getUserCountZone();

        assertEquals(5, results.size());
        assertEquals("Front Developer", results.get(0)[0]);
        assertEquals(2L, results.get(0)[1]);
        assertEquals("Backend Developer", results.get(1)[0]);
        assertEquals(1L, results.get(1)[1]);
    }

    @Test
    public void testFindAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        assertThat(users).hasSize(3);
        assertThat(users).extracting(UserEntity::getNombre).containsExactlyInAnyOrder("Juan Pérez", "Ana Gómez", "Luis Martínez");
    }

    @Test
    public void testFindById() {
        Optional<UserEntity> foundUser = userRepository.findById(1L);
        if (foundUser.isPresent()) {
            assertThat(foundUser.get().getNombre()).isEqualTo("Juan Pérez");
        }
    }
}