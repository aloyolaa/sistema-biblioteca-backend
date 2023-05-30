package com.republicaargentina.sistemabibliotecabackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.republicaargentina.sistemabibliotecabackend.entity.Usuario}
 */
public record UsuarioDto(Long id, @Size(min = 4) @NotBlank String username, @Email @NotBlank String email,
                         @NotBlank String nombres, @NotBlank String apellidos, Boolean habilitado,
                         List<RolDto> roles) implements Serializable {
    /**
     * DTO for {@link com.republicaargentina.sistemabibliotecabackend.entity.Rol}
     */
    public record RolDto(Long id, @Size(min = 4) @NotBlank String nombre) implements Serializable {
    }
}