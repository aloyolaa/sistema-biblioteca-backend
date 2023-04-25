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
@Table(name = "prestamo_libro")
public class PrestamoLibro extends BaseEntity {
    @NotNull(message = "{NotNull.prestamoLibro.fechaPrestamo}")
    @Column(name = "fecha_prestamo")
    private LocalDateTime fechaPrestamo;

    @Column(name = "fecha_devolucion")
    private LocalDateTime fechaDevolucion;

    @NotBlank(message = "{NotBlank.prestamoLibro.descripcion}")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Max(value = 6, message = "{Max.prestamoLibro.grado}")
    @Min(value = 1, message = "{Min.prestamoLibro.grado}")
    @NotNull(message = "{NotNull.prestamoLibro.grado}")
    @Column(name = "grado", nullable = false)
    private Integer grado;

    @NotBlank(message = "{NotBlank.prestamoLibro.seccion}")
    @Column(name = "seccion", nullable = false)
    private String seccion;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "observaciones", length = 1000)
    private String observaciones;

    @NotNull(message = "{NotNull.prestamoLibro.docente}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "docente_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Docente docente;

    @Size(min = 1, message = "{Size.prestamoLibro.detalle}")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "prestamo_libro_id", nullable = false)
    private List<DetallePrestamoLibro> detalle = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.estado = "PENDIENTE";
    }
}