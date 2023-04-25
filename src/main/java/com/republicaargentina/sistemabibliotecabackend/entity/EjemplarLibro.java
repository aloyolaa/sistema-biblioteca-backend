package com.republicaargentina.sistemabibliotecabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ejemplar_libro", uniqueConstraints = {
        @UniqueConstraint(name = "uc_ejemplar_libro_libro_id_numero", columnNames = {"libro_id", "numero"})
})
public class EjemplarLibro extends BaseEntity {
    @NotNull(message = "{NotNull.ejemplarLibro.numero}")
    @Column(name = "numero", updatable = false)
    private Integer numero;

    @Column(name = "estado")
    private String estado;

    @Column(name = "observaciones", length = 1000)
    private String observaciones;

    @NotNull(message = "{NotNull.ejemplarLibro.libro}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "libro_id", nullable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Libro libro;

    @PrePersist
    public void prePersist() {
        this.estado = "DISPONIBLE";
    }
}