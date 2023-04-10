package com.republicaargentina.sistemabibliotecabackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "rol", uniqueConstraints = {
        @UniqueConstraint(name = "uc_rol_nombre", columnNames = {"nombre"})
})
public class Rol extends BaseEntity {
    @Size(min = 4)
    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rol rol = (Rol) o;
        return getId() != null && Objects.equals(getId(), rol.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}