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
                @UniqueConstraint(name = "uc_docente_dni", columnNames = { "dni" })
})
public class Docente extends BaseEntity {
        @Size(min = 3, message = "{Size.docente.nombre}")
        @NotBlank(message = "{NotBlank.docente.nombre}")
        @Column(name = "nombre", nullable = false)
        private String nombre;

        @Size(min = 3, message = "{Size.docente.apellido}")
        @NotBlank(message = "{NotBlank.docente.apellido}")
        @Column(name = "apellido", nullable = false)
        private String apellido;

        @Size(min = 8, message = "{Size.docente.dni}")
        @NotBlank(message = "{NotBlank.docente.dni}")
        @Column(name = "dni", nullable = false)
        private String dni;

        @Size(min = 9, message = "{Size.docente.telefono}")
        @NotBlank(message = "{NotBlank.docente.telefono}")
        @Column(name = "telefono", nullable = false)
        private String telefono;
}