package com.republicaargentina.sistemabibliotecabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "libro", uniqueConstraints = {
        @UniqueConstraint(name = "uc_libro_titulo", columnNames = {"titulo"})
})
public class Libro extends BaseEntity {
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

    @Min(value = 1, message = "{Min.libro.cantidad}")
    @NotNull(message = "{NotNull.libro.cantidad}")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "observaciones")
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
}