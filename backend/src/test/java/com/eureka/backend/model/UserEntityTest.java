package com.eureka.backend.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class UserEntityTest {

    @Test
    public void testUserEntityBuilder() {

        ZoneEntity zone = new ZoneEntity(1L, "Zona 1", null);
        UserEntity user = UserEntity.builder()
                .id(1L)
                .nombre("Juan Pérez")
                .email("juan.perez@example.com")
                .zona(zone)
                .build();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getNombre()).isEqualTo("Juan Pérez");
        assertThat(user.getEmail()).isEqualTo("juan.perez@example.com");
        assertThat(user.getZona()).isEqualTo(zone);
    }

    @Test
    public void testUserEntityNoArgsConstructor() {

        UserEntity user = new UserEntity();
        user.setId(2L);
        user.setNombre("Ana Gómez");
        user.setEmail("ana.gomez@example.com");
        assertThat(user.getId()).isEqualTo(2L);
        assertThat(user.getNombre()).isEqualTo("Ana Gómez");
        assertThat(user.getEmail()).isEqualTo("ana.gomez@example.com");

    }

    @Test
    public void testUserEntityEquality() {

        ZoneEntity zone = new ZoneEntity(1L, "Zona 1", null);
        UserEntity user1 = UserEntity.builder()
                .id(3L)
                .nombre("Luis Martínez")
                .email("luis.martinez@example.com")
                .zona(zone)
                .build();
        UserEntity user2 = UserEntity.builder()
                .id(3L)
                .nombre("Luis Martínez")
                .email("luis.martinez@example.com")
                .zona(zone)
                .build();
        assertThat(user1).isEqualTo(user2);
        
    }
}