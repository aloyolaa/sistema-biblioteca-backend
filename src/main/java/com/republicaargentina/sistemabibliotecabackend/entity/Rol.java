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
@Table(name = "rol", uniqueConstraints = {
        @UniqueConstraint(name = "uc_rol_nombre", columnNames = { "nombre" })
})
public class Rol extends BaseEntity {
    @Size(min = 4)
    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;
}