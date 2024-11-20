package com.eureka.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eureka.backend.model.ZoneEntity;
import com.eureka.backend.repository.ZoneRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<ZoneEntity> findAll() {
        return zoneRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ZoneEntity> findById(Long id) {
        return zoneRepository.findById(id);
    }

    @Override
    @Transactional
    public ZoneEntity save(ZoneEntity zoneEntity) {
        return zoneRepository.save(zoneEntity);
    }

}
