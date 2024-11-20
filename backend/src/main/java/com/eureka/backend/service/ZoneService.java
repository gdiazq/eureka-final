package com.eureka.backend.service;

import java.util.Optional;

import com.eureka.backend.model.ZoneEntity;

public interface ZoneService {

    Iterable<ZoneEntity> findAll();

    Optional <ZoneEntity> findById(Long id);

    ZoneEntity save(ZoneEntity zoneEntity);

}
