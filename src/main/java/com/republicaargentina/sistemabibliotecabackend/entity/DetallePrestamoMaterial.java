package com.republicaargentina.sistemabibliotecabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detalle_prestamo_material")
public class DetallePrestamoMaterial extends BaseEntity {
    @NotNull(message = "{NotNull.detallePrestamoMaterial.ejemplarMaterial}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ejemplar_material_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private EjemplarMaterial ejemplarMaterial;

    public String getCodigo() {
        return this.ejemplarMaterial.getMaterial().getCodigo() + "-" + this.ejemplarMaterial.getNumero();
    }

    public String getNombre() {
        return this.ejemplarMaterial.getMaterial().getNombre();
    }

    public String getEstado() {
        return this.ejemplarMaterial.getEstado();
    }
}