package com.republicaargentina.sistemabibliotecabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "libro", indexes = {
        @Index(name = "idx_libro_codigo", columnList = "codigo"),
        @Index(name = "idx_libro_titulo", columnList = "titulo")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_libro_codigo", columnNames = {"codigo"})
})
public class Libro extends BaseEntity {
    @Size(min = 4, message = "{Size.libro.codigo}")
    @NotBlank(message = "{NotBlank.libro.codigo}")
    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Size(min = 4, message = "{Size.libro.titulo}")
    @NotBlank(message = "{NotBlank.libro.titulo}")
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Max(value = 2023, message = "{Max.libro.anio}")
    @Min(value = 1, message = "{Min.libro.anio}")
    @NotNull(message = "{NotNull.libro.anio}")
    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Max(value = 6, message = "{Max.libro.grado}")
    @Min(value = 1, message = "{Min.libro.grado}")
    @NotNull(message = "{NotNull.libro.grado}")
    @Column(name = "grado", nullable = false)
    private Integer grado;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "observaciones", length = 1000)
    private String observaciones;

    @NotNull(message = "{NotNull.libro.autor}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autor_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Autor autor;

    @NotNull(message = "{NotNull.libro.categoria}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Categoria categoria;

    @NotNull(message = "{NotNull.libro.editorial}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "editorial_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Editorial editorial;

    @NotNull(message = "{NotNull.libro.area}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Area area;

    @PrePersist
    public void prePersist() {
        this.estado = "DISPONIBLE";
    }
}