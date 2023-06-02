package com.republicaargentina.sistemabibliotecabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "libro", indexes = {
        @Index(name = "idx_libro_codigo", columnList = "codigo"),
        @Index(name = "idx_libro_titulo", columnList = "titulo")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_libro_codigo", columnNames = { "codigo" })
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

    @Min(value = 1, message = "{Min.libro.anio}")
    @NotNull(message = "{NotNull.libro.anio}")
    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Max(value = 6, message = "{Max.libro.grado}")
    @Min(value = 1, message = "{Min.libro.grado}")
    @NotNull(message = "{NotNull.libro.grado}")
    @Column(name = "grado", nullable = false)
    private Integer grado;

    @NotNull(message = "{NotNull.libro.area}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Area area;

    @NotNull(message = "{NotNull.libro.categoria}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Categoria categoria;

    @NotNull(message = "{NotNull.libro.editorial}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "editorial_id", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Editorial editorial;

    @Size(min = 1, message = "{Size.libro.autores}")
    @ManyToMany
    @JoinTable(name = "libro_autor", joinColumns = @JoinColumn(name = "libro_id"), inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Autor> autores = new LinkedHashSet<>();

    public String getAreaNombre() {
        return this.area.getNombre();
    }

    public String getCategoriaNombre() {
        return this.categoria.getNombre();
    }

    public String getEditorialNombre() {
        return this.editorial.getNombre();
    }

    public String getAutoresNombres() {
        StringBuilder autoresNombres = new StringBuilder();
        for (Autor autor : this.autores) {
            autoresNombres.append(autor.getNombre())
                    .append(" ")
                    .append(autor.getApellido());
            if (this.autores.size() > 1) {
                autoresNombres.append(", ");
            }
        }
        return autoresNombres.toString();
    }
}