package com.portable.microservices.ms_inventory.locations.presentation.controllers;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.CreateLocationPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.DeleteLocationPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.GetLocationPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.ListLocationsPortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.UpdateLocationPortIn;
import com.portable.microservices.ms_inventory.locations.presentation.dto.LocationDto;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    private final ListLocationsPortIn listUseCase;
    private final GetLocationPortIn getUseCase;
    private final CreateLocationPortIn createUseCase;
    private final UpdateLocationPortIn updateUseCase;
    private final DeleteLocationPortIn deleteUseCase;

    public LocationController(ListLocationsPortIn listUseCase,
                              GetLocationPortIn getUseCase,
                              CreateLocationPortIn createUseCase,
                              UpdateLocationPortIn updateUseCase,
                              DeleteLocationPortIn deleteUseCase) {
        this.listUseCase = listUseCase;
        this.getUseCase = getUseCase;
        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @GetMapping
    public List<LocationDto> list() {
        return listUseCase.execute().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> get(@PathVariable Long id) {
        try {
            Location l = getUseCase.execute(id);
            return ResponseEntity.ok(toDto(l));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<LocationDto> create(@Valid @RequestBody LocationDto dto) {
        var command = new CreateLocationPortIn.CreateLocationCommand(dto.getName(), dto.getDescription());
        Location created = createUseCase.execute(command);
        return ResponseEntity.created(URI.create("/api/v1/locations/" + created.getId())).body(toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDto> update(@PathVariable Long id, @Valid @RequestBody LocationDto dto) {
        var command = new UpdateLocationPortIn.UpdateLocationCommand(id, dto.getName(), dto.getDescription());
        try {
            Location updated = updateUseCase.execute(command);
            return ResponseEntity.ok(toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    private LocationDto toDto(Location l) {
        return new LocationDto(l.getId(), l.getName(), l.getDescription());
    }
}
