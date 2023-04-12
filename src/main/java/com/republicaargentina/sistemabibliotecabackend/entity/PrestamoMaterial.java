package com.republicaargentina.sistemabibliotecabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "prestamo_material")
public class PrestamoMaterial extends BaseEntity {
    @Column(name = "fecha_prestamo")
    private LocalDateTime fechaPrestamo;

    @NotNull(message = "{}")
    @Column(name = "fecha_limite", nullable = false)
    private LocalDateTime fechaLimite;

    @Column(name = "fecha_devolucion", nullable = false)
    private LocalDateTime fechaDevolucion;

    @Max(value = 6, message = "{}")
    @Min(value = 1, message = "{}")
    @NotNull(message = "{}")
    @Column(name = "grado", nullable = false)
    private Integer grado;

    @NotBlank(message = "{}")
    @Column(name = "seccion", nullable = false)
    private String seccion;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "observaciones", length = 1000)
    private String observaciones;

    @NotNull(message = "{}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "docente_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Docente docente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "prestamo_material_id", nullable = false)
    private List<DetallePrestamoMaterial> detalle = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.fechaPrestamo = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }
}