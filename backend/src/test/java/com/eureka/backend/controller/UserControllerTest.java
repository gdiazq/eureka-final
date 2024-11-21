package com.eureka.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import com.eureka.backend.dto.UserDto;
import com.eureka.backend.model.UserEntity;
import com.eureka.backend.model.ZoneEntity;
import com.eureka.backend.service.UserService;
import com.eureka.backend.service.ZoneService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private ZoneService zoneService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    // 1. Prueba para obtener todos los usuarios
    @Test
    public void testFindAll() throws Exception {
        UserEntity user1 = new UserEntity(1L, "Juan Pérez", "juan.perez@example.com", null);
        UserEntity user2 = new UserEntity(2L, "Ana Gómez", "ana.gomez@example.com", null);

        when(userService.findAll()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$[1].nombre").value("Ana Gómez"));

        verify(userService, times(1)).findAll();
    }

    // 2. Prueba para obtener un usuario por su ID
    @Test
    public void testFindById() throws Exception {
        UserEntity user = new UserEntity(1L, "Juan Pérez", "juan.perez@example.com", null);

        when(userService.findById(anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"));

        verify(userService, times(1)).findById(anyLong());
    }

    // 3. Prueba para crear un nuevo usuario
    @Test
    public void testCreateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setNombre("Juan Pérez");
        userDto.setEmail("juan.perez@example.com");
        userDto.setZonaId(1L);

        ZoneEntity zone = new ZoneEntity(1L, "Zona 1", null);
        UserEntity createdUser = new UserEntity(1L, "Juan Pérez", "juan.perez@example.com", zone);

        when(zoneService.findById(anyLong())).thenReturn(Optional.of(zone));
        when(userService.save(any(UserEntity.class))).thenReturn(createdUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Juan Pérez\",\"email\":\"juan.perez@example.com\",\"zonaId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"));

        verify(zoneService, times(1)).findById(anyLong());
        verify(userService, times(1)).save(any(UserEntity.class));
    }
}