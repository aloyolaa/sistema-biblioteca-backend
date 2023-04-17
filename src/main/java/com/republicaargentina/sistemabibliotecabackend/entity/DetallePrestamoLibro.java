package com.republicaargentina.sistemabibliotecabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detalle_prestamo_libro")
public class DetallePrestamoLibro extends BaseEntity {
    @NotNull(message = "{NotNull.detallePrestamoLibro.ejemplar}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ejemplar_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Ejemplar ejemplar;
}