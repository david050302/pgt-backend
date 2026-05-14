package com.portable.microservices.ms_inventory.locations.domain.ports.in;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;

public interface UpdateLocationPortIn {

    record UpdateLocationCommand(Long id, String name, String description) {}

    Location execute(UpdateLocationCommand command);
}
