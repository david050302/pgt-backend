package com.portable.microservices.ms_inventory.locations.infrastructure.persistence.adapter;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;
import com.portable.microservices.ms_inventory.locations.domain.ports.out.LocationPersistencePortOut;
import com.portable.microservices.ms_inventory.locations.infrastructure.persistence.mapper.LocationPersistenceMapper;
import com.portable.microservices.ms_inventory.locations.infrastructure.persistence.entity.LocationJpaEntity;
import com.portable.microservices.ms_inventory.locations.infrastructure.persistence.repository.LocationJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LocationPersistenceAdapter implements LocationPersistencePortOut {

    private final LocationJpaRepository repository;

    public LocationPersistenceAdapter(LocationJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Location> findAll() {
        return repository.findAll().stream().map(LocationPersistenceMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Location> findById(Long id) {
        return repository.findById(id).map(LocationPersistenceMapper::toDomain);
    }

    @Override
    public Location save(Location location) {
        LocationJpaEntity entity = LocationPersistenceMapper.toEntity(location);
        LocationJpaEntity saved = repository.save(entity);
        return LocationPersistenceMapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
