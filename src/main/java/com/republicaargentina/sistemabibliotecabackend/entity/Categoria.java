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
@Table(name = "categoria", uniqueConstraints = {
        @UniqueConstraint(name = "uc_categoria_nombre", columnNames = {"nombre"})
})
public class Categoria extends BaseEntity {
    @Size(min = 4, message = "{Size.categoria.nombre}")
    @NotBlank(message = "{NotBlank.categoria.nombre}")
    @Column(name = "nombre", nullable = false)
    private String nombre;
}