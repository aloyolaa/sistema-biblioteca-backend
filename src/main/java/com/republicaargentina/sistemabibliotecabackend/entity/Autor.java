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
@Table(name = "autor", uniqueConstraints = {
        @UniqueConstraint(name = "uc_autor_nombre", columnNames = {"nombre"})
})
public class Autor extends BaseEntity {
    @Size(min = 4, message = "{Size.autor.nombre}")
    @NotBlank(message = "{NotBlank.autor.nombre}")
    @Column(name = "nombre", nullable = false)
    private String nombre;
}