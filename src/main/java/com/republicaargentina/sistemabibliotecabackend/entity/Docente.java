package com.republicaargentina.sistemabibliotecabackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "docente", indexes = {
        @Index(name = "idx_docente_dni", columnList = "dni")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_docente_dni", columnNames = {"dni"})
})
public class Docente extends BaseEntity {
    @Size(min = 4, message = "{}")
    @NotBlank(message = "{}")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Size(min = 4, message = "{}")
    @NotBlank(message = "{}")
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @NotBlank(message = "{}")
    @Column(name = "dni", nullable = false)
    private String dni;

    @NotBlank(message = "{}")
    @Column(name = "telefono", nullable = false)
    private String telefono;
}