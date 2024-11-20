package com.eureka.backend.repository;

import com.eureka.backend.model.UserEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT z.nombre, count(u) FROM UserEntity u JOIN u.zona z GROUP BY z.id")
    List<Object[]> getUserCountZone();
}    

