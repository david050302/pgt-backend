package com.portable.microservices.ms_inventory.locations.application.usecases;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.UpdateLocationPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.UpdateLocationPortIn.UpdateLocationCommand;
import com.portable.microservices.ms_inventory.locations.domain.ports.out.LocationPersistencePortOut;

public class UpdateLocationUseCase implements UpdateLocationPortIn {

    private final LocationPersistencePortOut persistence;

    public UpdateLocationUseCase(LocationPersistencePortOut persistence) {
        this.persistence = persistence;
    }

    @Override
    public Location execute(UpdateLocationCommand command) {
        var existing = persistence.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        Location updated = new Location(existing.getId(), command.name(), command.description(), existing.getCreatedAt());
        return persistence.save(updated);
    }
}
