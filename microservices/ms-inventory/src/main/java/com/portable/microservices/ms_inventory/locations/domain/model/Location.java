package com.portable.microservices.ms_inventory.locations.domain.model;

import java.util.UUID;

public record Location(
    UUID idLocacion,
    Long idAlmacen, // FK hacia warehouse
    String zona,
    String pasillo,
    String estante,
    String codBarras,
    Integer capacidad,
    Boolean activo
) {
    public boolean isValidForCreation() {
        return idAlmacen != null && codBarras != null && !codBarras.isBlank();
    }
}