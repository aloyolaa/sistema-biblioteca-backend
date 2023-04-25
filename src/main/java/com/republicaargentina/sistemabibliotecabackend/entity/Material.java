package com.republicaargentina.sistemabibliotecabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "material", indexes = {
        @Index(name = "idx_material_codigo", columnList = "codigo"),
        @Index(name = "idx_material_nombre", columnList = "nombre")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_material_codigo", columnNames = {"codigo"})
})
public class Material extends BaseEntity {
    @Size(min = 4, message = "{Size.material.codigo}")
    @NotBlank(message = "{NotBlank.material.codigo}")
    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Size(min = 4, message = "{Size.material.nombre}")
    @NotBlank(message = "{NotBlank.material.nombre}")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "medidas")
    private String medidas;

    @NotNull(message = "{NotNull.material.area}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Area area;
}