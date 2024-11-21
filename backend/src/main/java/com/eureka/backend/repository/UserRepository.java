package com.eureka.backend.repository;

import com.eureka.backend.model.UserEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT z.nombre, COALESCE(count(u), 0) FROM ZoneEntity z LEFT JOIN UserEntity u ON u.zona.id = z.id GROUP BY z.id")
    List<Object[]> getUserCountZone();
}    

