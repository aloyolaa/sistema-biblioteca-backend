package com.republicaargentina.sistemabibliotecabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ejemplar_material", uniqueConstraints = {
        @UniqueConstraint(name = "uc_ejemplar_material_material_id_numero", columnNames = {"material_id", "numero"})
})
public class EjemplarMaterial extends BaseEntity {
    @NotNull(message = "{NotNull.ejemplarMaterial.numero}")
    @Column(name = "numero", updatable = false)
    private Integer numero;

    @Column(name = "estado")
    private String estado;

    @Column(name = "observaciones", length = 1000)
    private String observaciones;

    @NotNull(message = "{NotNull.ejemplarMaterial.material}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_id", nullable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Material material;

    @PrePersist
    public void prePersist() {
        this.estado = "DISPONIBLE";
    }
}