package com.portable.microservices.ms_inventory.locations.infrastructure.persistence.mapper;

import com.portable.microservices.ms_inventory.locations.domain.model.Location;
import com.portable.microservices.ms_inventory.locations.infrastructure.persistence.entity.LocationJpaEntity;

import java.time.LocalDateTime;

public class LocationPersistenceMapper {

    public static Location toDomain(LocationJpaEntity e) {
        return new Location(e.getId(), e.getName(), e.getDescription(), e.getCreatedAt());
    }

    public static LocationJpaEntity toEntity(Location d) {
        LocationJpaEntity e = new LocationJpaEntity();
        if (d.getId() != null) {
            e.setId(d.getId());
        }
        e.setName(d.getName());
        e.setDescription(d.getDescription());
        e.setCreatedAt(d.getCreatedAt() != null ? d.getCreatedAt() : LocalDateTime.now());
        return e;
    }
}
