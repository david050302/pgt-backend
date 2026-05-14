package com.portable.microservices.ms_inventory.locations.application.usecases;

import com.portable.microservices.ms_inventory.locations.domain.ports.in.DeleteLocationPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.out.LocationPersistencePortOut;

public class DeleteLocationUseCase implements DeleteLocationPortIn {

    private final LocationPersistencePortOut persistence;

    public DeleteLocationUseCase(LocationPersistencePortOut persistence) {
        this.persistence = persistence;
    }

    @Override
    public void execute(Long id) {
        persistence.deleteById(id);
    }
}
