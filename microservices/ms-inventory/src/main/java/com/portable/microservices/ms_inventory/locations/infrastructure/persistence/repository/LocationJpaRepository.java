package com.portable.microservices.ms_inventory.locations.infrastructure.persistence.repository;

import com.portable.microservices.ms_inventory.locations.infrastructure.persistence.entity.LocationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationJpaRepository extends JpaRepository<LocationJpaEntity, Long> {

}
