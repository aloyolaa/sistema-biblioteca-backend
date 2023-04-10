package com.republicaargentina.sistemabibliotecabackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "editorial", uniqueConstraints = {
        @UniqueConstraint(name = "uc_editorial_nombre", columnNames = {"nombre"})
})
public class Editorial extends BaseEntity {
    @Size(min = 4, message = "{Size.editorial.nombre}")
    @NotBlank(message = "{NotBlank.editorial.nombre}")
    @Column(name = "nombre", nullable = false)
    private String nombre;
}