package com.portable.microservices.ms_inventory.locations.infrastructure.persistence.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "locacion", schema = "inventory", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cod_barras"})
})
public class LocationJpaEntity {

    @Id
    @Column(name = "id_locacion", columnDefinition = "uuid")
    @UuidGenerator
    private UUID idLocacion;

    @Column(name = "id_almacen", nullable = false)
    private Long idAlmacen;

    @Column(name = "zona")
    private String zona;

    @Column(name = "pasillo")
    private String pasillo;

    @Column(name = "estante")
    private String estante;

    @Column(name = "cod_barras", nullable = false, unique = true)
    private String codBarras;

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "activo")
    private Boolean activo = Boolean.TRUE;

    public LocationJpaEntity() {}

    public UUID getIdLocacion() {
        return idLocacion;
    }

    public void setIdLocacion(UUID idLocacion) {
        this.idLocacion = idLocacion;
    }

    public Long getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Long idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getPasillo() {
        return pasillo;
    }

    public void setPasillo(String pasillo) {
        this.pasillo = pasillo;
    }

    public String getEstante() {
        return estante;
    }

    public void setEstante(String estante) {
        this.estante = estante;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
