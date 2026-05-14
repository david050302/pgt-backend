package com.portable.microservices.ms_inventory.locations.application.usecases;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.ListLocationsPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.out.LocationPersistencePortOut;

import java.util.List;

public class ListLocationsUseCase implements ListLocationsPortIn {

    private final LocationPersistencePortOut persistence;

    public ListLocationsUseCase(LocationPersistencePortOut persistence) {
        this.persistence = persistence;
    }

    @Override
    public List<Location> execute() {
        return persistence.findAll();
    }
}
