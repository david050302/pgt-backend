package com.portable.microservices.ms_inventory.locations.domain.ports.in;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;

import java.util.List;

public interface ListLocationsPortIn {
    List<Location> execute();
}
