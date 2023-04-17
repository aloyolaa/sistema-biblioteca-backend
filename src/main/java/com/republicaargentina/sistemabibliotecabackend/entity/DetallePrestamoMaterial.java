package com.republicaargentina.sistemabibliotecabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detalle_prestamo_material")
public class DetallePrestamoMaterial extends BaseEntity {
    @Min(value = 1, message = "{Min.detallePrestamoMaterial.cantidad}")
    @NotNull(message = "{NotNull.detallePrestamoMaterial.cantidad}")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull(message = "{NotNull.detallePrestamoMaterial.material}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Material material;
}