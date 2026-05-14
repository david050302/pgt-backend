package com.portable.microservices.ms_inventory.locations.domain.ports.in;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;

public interface CreateLocationPortIn {

    record CreateLocationCommand(String name, String description) {}

    Location execute(CreateLocationCommand command);
}
