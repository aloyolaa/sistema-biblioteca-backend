package com.republicaargentina.sistemabibliotecabackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.republicaargentina.sistemabibliotecabackend.entity.Usuario}
 */
public record UsuarioDto(Long id, @Size(min = 4, message = "{Size.usuario.username}") @NotBlank(message = "{NotBlank.usuario.username}") String username,
                         @Email @NotBlank(message = "{NotBlank.usuario.email}") String email, @NotBlank(message = "{NotBlank.usuario.nombres}") String nombres,
                         @NotBlank(message = "{NotBlank.usuario.apellidos}") String apellidos, Boolean habilitado,
                         @Size(min = 1, message = "{Size.usuario.roles}") List<RolDto> roles) implements Serializable {
    /**
     * DTO for {@link com.republicaargentina.sistemabibliotecabackend.entity.Rol}
     */
    public record RolDto(Long id, @Size(min = 4) @NotBlank String nombre) implements Serializable {
    }
}