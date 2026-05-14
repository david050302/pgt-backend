package com.portable.microservices.ms_inventory.locations.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.portable.microservices.ms_inventory.locations.application.usecases.CreateLocationUseCase;
import com.portable.microservices.ms_inventory.locations.application.usecases.DeleteLocationUseCase;
import com.portable.microservices.ms_inventory.locations.application.usecases.GetLocationUseCase;
import com.portable.microservices.ms_inventory.locations.application.usecases.ListLocationsUseCase;
import com.portable.microservices.ms_inventory.locations.application.usecases.UpdateLocationUseCase;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.CreateLocationPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.DeleteLocationPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.GetLocationPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.ListLocationsPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.UpdateLocationPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.out.LocationPersistencePortOut;

@Configuration
public class InventoryLocationsConfig {

    @Bean
    public ListLocationsPortIn listLocationsPortIn(LocationPersistencePortOut persistence) {
        return new ListLocationsUseCase(persistence);
    }

    @Bean
    public GetLocationPortIn getLocationPortIn(LocationPersistencePortOut persistence) {
        return new GetLocationUseCase(persistence);
    }

    @Bean
    public CreateLocationPortIn createLocationPortIn(LocationPersistencePortOut persistence) {
        return new CreateLocationUseCase(persistence);
    }

    @Bean
    public UpdateLocationPortIn updateLocationPortIn(LocationPersistencePortOut persistence) {
        return new UpdateLocationUseCase(persistence);
    }

    @Bean
    public DeleteLocationPortIn deleteLocationPortIn(LocationPersistencePortOut persistence) {
        return new DeleteLocationUseCase(persistence);
    }
}
