package com.portable.microservices.ms_inventory.locations.domain.ports.in;

public interface DeleteLocationPortIn {
    void execute(Long id);
}
