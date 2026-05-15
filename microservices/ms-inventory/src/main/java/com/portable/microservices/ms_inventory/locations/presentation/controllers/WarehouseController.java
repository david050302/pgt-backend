package com.portable.microservices.ms_inventory.locations.presentation.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.microservices.ms_inventory.locations.domain.ports.in.warehouse.CreateWarehousePortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.warehouse.DeleteWarehousePortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.warehouse.GetWarehousePortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.warehouse.ListWarehousePortIn;
import com.portable.microservices.ms_inventory.locations.domain.ports.in.warehouse.UpdateWarehousePortIn;
import com.portable.microservices.ms_inventory.locations.presentation.dto.CreateWarehouseRequest;
import com.portable.microservices.ms_inventory.locations.presentation.dto.UpdateWarehouseRequest;
import com.portable.microservices.ms_inventory.locations.presentation.dto.WarehouseResponse;
import com.portable.microservices.ms_inventory.locations.presentation.mapper.WarehouseWebMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

    private final CreateWarehousePortIn createWarehousePortIn;
    private final UpdateWarehousePortIn updateWarehousePortIn;
    private final GetWarehousePortIn getWarehousePortIn;
    private final ListWarehousePortIn listWarehousePortIn;
    private final DeleteWarehousePortIn deleteWarehousePortIn;
    private final WarehouseWebMapper mapper;

    @PostMapping
    public ResponseEntity<WarehouseResponse> create(@Valid @RequestBody CreateWarehouseRequest request) {
        var domain = mapper.toDomain(request);
        var created = createWarehousePortIn.execute(domain);
        var response = mapper.toResponse(created);
        return ResponseEntity.ok(response);
    }
@PutMapping("/{id}")
    public ResponseEntity<WarehouseResponse> update(@PathVariable Long id, @RequestBody UpdateWarehouseRequest request) {
        var domain = mapper.toDomain(request);
        var updated = updateWarehousePortIn.execute(id, domain);
        var response = mapper.toResponse(updated);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponse> getById(@PathVariable Long id) {
        var warehouse = getWarehousePortIn.execute(id);
        var response = mapper.toResponse(warehouse);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<WarehouseResponse>> getAll() {
        var warehouses = listWarehousePortIn.execute();
        var responses = warehouses.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteWarehousePortIn.execute(id);
        return ResponseEntity.noContent().build();
    }
}