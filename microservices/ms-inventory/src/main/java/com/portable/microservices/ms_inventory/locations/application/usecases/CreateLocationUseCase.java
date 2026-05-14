package com.portable.microservices.ms_inventory.locations.application.usecases;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.CreateLocationPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.out.LocationPersistencePortOut;

public class CreateLocationUseCase implements CreateLocationPortIn {

    private final LocationPersistencePortOut persistence;

    public CreateLocationUseCase(LocationPersistencePortOut persistence) {
        this.persistence = persistence;
    }

    @Override
    public Location execute(CreateLocationCommand command) {
        Location toCreate = new Location(null, command.name(), command.description(), null);
        return persistence.save(toCreate);
    }
}
