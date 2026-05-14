package com.portable.microservices.ms_inventory.locations.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;
import com.portable.microservices.ms_inventory.locations.domain.ports.out.LocationPersistencePortOut;
import com.portable.microservices.ms_inventory.locations.infrastructure.persistence.mapper.LocationPersistenceMapper;
import com.portable.microservices.ms_inventory.locations.infrastructure.persistence.repository.LocationJpaRepository;

@Component
public class LocationPersistenceAdapter implements LocationPersistencePortOut {

    private final LocationJpaRepository repository;

    private final LocationPersistenceMapper mapper;

    public LocationPersistenceAdapter(LocationJpaRepository repository, LocationPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public boolean existsByCodBarras(String codBarras) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Location> findAllActives() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Location> findById(UUID idLocacion) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Location save(Location location) {
        // TODO Auto-generated method stub
        return null;
    }


}
