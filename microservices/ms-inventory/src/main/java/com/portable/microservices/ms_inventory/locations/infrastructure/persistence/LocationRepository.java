package com.portable.microservices.ms_inventory.locations.infrastructure.persistence;

import com.portable.microservices.ms_inventory.locations.infrastructure.persistence.entity.LocationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<LocationJpaEntity, Long> {

}
