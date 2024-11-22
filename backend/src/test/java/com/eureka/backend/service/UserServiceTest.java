package com.eureka.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    // 1. Prueba para obtener la cantidad de usuarios por zona
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
