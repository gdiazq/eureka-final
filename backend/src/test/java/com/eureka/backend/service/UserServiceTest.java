package com.eureka.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eureka.backend.model.UserEntity;
import com.eureka.backend.repository.UserRepository;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 1. Prueba para obtener todos los usuarios
    @Test
    public void testFindAll() {
        UserEntity user1 = new UserEntity(1L, "Juan Pérez", "juan.perez@example.com", null);
        UserEntity user2 = new UserEntity(2L, "Ana Gómez", "ana.gomez@example.com", null);
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        Iterable<UserEntity> users = userService.findAll();
        assertNotNull(users);
        assertEquals(2, ((List<UserEntity>) users).size());
        verify(userRepository, times(1)).findAll();
    }

    // 2. Prueba para obtener un usuario por su id
    @Test
    public void testFindById() {
        UserEntity user = new UserEntity(1L, "Juan Pérez", "juan.perez@example.com", null);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        Optional<UserEntity> foundUser = userService.findById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals("Juan Pérez", foundUser.get().getNombre());
        verify(userRepository, times(1)).findById(anyLong());
    }

    // 3. Prueba para obtener la cantidad de usuarios por zona
    @Test
    public void testGetUserCountZone() {
        Object[] zoneCount1 = new Object[]{"Zona 1", 3};
        Object[] zoneCount2 = new Object[]{"Zona 2", 0};
        when(userRepository.getUserCountZone()).thenReturn(Arrays.asList(zoneCount1, zoneCount2));
        List<Object[]> counts = userService.getUserCountZone();
        assertNotNull(counts);
        assertEquals(2, counts.size());
        assertEquals("Zona 1", counts.get(0)[0]);
        assertEquals(3, counts.get(0)[1]);
        assertEquals("Zona 2", counts.get(1)[0]);
        assertEquals(0, counts.get(1)[1]);
        verify(userRepository, times(1)).getUserCountZone();
    }
}
