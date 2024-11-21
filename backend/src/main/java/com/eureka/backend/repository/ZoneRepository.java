package com.eureka.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eureka.backend.model.ZoneEntity;

public interface ZoneRepository extends JpaRepository<ZoneEntity, Long> {

}
