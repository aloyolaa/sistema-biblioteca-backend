package com.republicaargentina.sistemabibliotecabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "material", uniqueConstraints = {
        @UniqueConstraint(name = "uc_material_nombre", columnNames = {"nombre"})
})
public class Material extends BaseEntity {
    @Size(min = 4, message = "{Size.material.nombre}")
    @NotBlank(message = "{NotBlank.material.nombre}")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "medidas")
    private String medidas;

    @Min(value = 1, message = "{Min.material.cantidadInicial}")
    @NotNull(message = "{NotNull.material.cantidadInicial}")
    @Column(name = "cantidad_inicial", nullable = false)
    private Integer cantidadInicial;

    @Column(name = "cantidad_actual")
    private Integer cantidadActual;

    @Column(name = "observaciones", length = 1000)
    private String observaciones;

    @NotNull(message = "{NotNull.material.area}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Area area;

    @PrePersist
    public void prePersist() {
        this.cantidadActual = this.cantidadInicial;
    }
}