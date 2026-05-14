package com.portable.microservices.ms_inventory.locations.application.usecases;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.GetLocationPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.out.LocationPersistencePortOut;

public class GetLocationUseCase implements GetLocationPortIn {

    private final LocationPersistencePortOut persistence;

    public GetLocationUseCase(LocationPersistencePortOut persistence) {
        this.persistence = persistence;
    }

    @Override
    public Location execute(Long id) {
        return persistence.findById(id).orElseThrow(() -> new IllegalArgumentException("Location not found"));
    }
}
