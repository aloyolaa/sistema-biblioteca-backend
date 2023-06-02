package com.republicaargentina.sistemabibliotecabackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(name = "uc_usuario_username", columnNames = {"username"}),
        @UniqueConstraint(name = "uc_usuario_email", columnNames = {"email"})
})
public class Usuario extends BaseEntity {
    @Size(min = 4, message = "{Size.usuario.username}")
    @NotBlank(message = "{NotBlank.usuario.username}")
    @Column(name = "username", nullable = false)
    private String username;

    @Size(min = 8, message = "{Size.usuario.password}")
    @NotBlank(message = "{NotBlank.usuario.password}")
    @Column(name = "password", nullable = false)
    private String password;

    @Email
    @NotBlank(message = "{NotBlank.usuario.email}")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "{NotBlank.usuario.nombres}")
    @Column(name = "nombres", nullable = false)
    private String nombres;

    @NotBlank(message = "{NotBlank.usuario.apellidos}")
    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "habilitado")
    private Boolean habilitado;

    @Size(min = 1, message = "{Size.usuario.roles}")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = {
                    @UniqueConstraint(name = "uc_usuario_id_rol_id", columnNames = {"usuario_id", "rol_id"})
            })
    private List<Rol> roles = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.habilitado = true;
    }
}