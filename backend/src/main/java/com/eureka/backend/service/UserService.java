package com.eureka.backend.service;

import java.util.List;
import java.util.Optional;

import com.eureka.backend.model.UserEntity;

public interface UserService {

    Iterable<UserEntity> findAll();

    Optional <UserEntity> findById(Long id);

    UserEntity save(UserEntity userEntity);

    List<Object[]> getUserCountZone();

}
