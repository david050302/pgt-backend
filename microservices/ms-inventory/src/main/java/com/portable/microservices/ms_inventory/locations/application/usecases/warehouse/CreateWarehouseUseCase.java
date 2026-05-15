package com.portable.microservices.ms_inventory.locations.application.usecases.warehouse;

import org.springframework.stereotype.Service;

import com.portable.microservices.ms_inventory.locations.domain.model.Warehouse;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.warehouse.CreateWarehousePortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.out.WarehousePersistencePortOut;

@Service
public class CreateWarehouseUseCase implements CreateWarehousePortIn{

    private WarehousePersistencePortOut persistence;
    public CreateWarehouseUseCase(WarehousePersistencePortOut persistence) {
        this.persistence = persistence;
    }

    @Override
    public Warehouse execute(Warehouse warehouse) {
        if (persistence.existsByCodAlm(warehouse.codAlm())) {
            throw new RuntimeException("El código de almacén ya existe");
        }
        return persistence.save(warehouse);
    }


}



