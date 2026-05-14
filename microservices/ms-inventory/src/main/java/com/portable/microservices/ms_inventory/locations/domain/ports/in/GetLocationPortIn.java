package com.portable.microservices.ms_inventory.locations.domain.ports.in;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;

import java.util.Optional;

public interface GetLocationPortIn {
    Location execute(Long id);
}
