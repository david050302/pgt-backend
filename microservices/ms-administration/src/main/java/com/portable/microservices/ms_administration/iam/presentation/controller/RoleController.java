package com.portable.microservices.ms_administration.iam.presentation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.microservices.ms_administration.iam.domain.model.Role;
import com.portable.microservices.ms_administration.iam.domain.ports.out.RolePersistencePortOut;
import com.portable.microservices.ms_administration.iam.presentation.dto.CreateRoleRequest;
import com.portable.microservices.ms_administration.iam.presentation.dto.RoleResponse;
import com.portable.shared.infrastructure.presentation.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RolePersistencePortOut rolePersistence;

    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> create(@Valid @RequestBody CreateRoleRequest request) {
        Role role = new Role(null, request.name(), null);
        Role saved = rolePersistence.save(role);
        RoleResponse resp = new RoleResponse(saved.id(), saved.name(), saved.createdAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Rol creado", resp));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponse>>> list() {
        var roles = rolePersistence.findAll();
        var responses = roles.stream().map(r -> new RoleResponse(r.id(), r.name(), r.createdAt())).toList();
        return ResponseEntity.ok(ApiResponse.ok("Roles listados", responses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> get(@PathVariable Long id) {
        var role = rolePersistence.findAll().stream().filter(r -> r.id()!=null && r.id().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
        return ResponseEntity.ok(ApiResponse.ok("Rol encontrado", new RoleResponse(role.id(), role.name(), role.createdAt())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> update(@PathVariable Long id, @Valid @RequestBody CreateRoleRequest request) {
        Role updated = new Role(id, request.name(), null);
        var saved = rolePersistence.save(updated);
        return ResponseEntity.ok(ApiResponse.ok("Rol actualizado", new RoleResponse(saved.id(), saved.name(), saved.createdAt())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rolePersistence.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
