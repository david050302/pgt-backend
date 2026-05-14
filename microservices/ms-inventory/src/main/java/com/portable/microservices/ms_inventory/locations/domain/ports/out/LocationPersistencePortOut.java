package com.portable.microservices.ms_inventory.locations.domain.ports.out;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationPersistencePortOut {
    List<Location> findAll();
    Optional<Location> findById(Long id);
    Location save(Location location);
    void deleteById(Long id);
}
