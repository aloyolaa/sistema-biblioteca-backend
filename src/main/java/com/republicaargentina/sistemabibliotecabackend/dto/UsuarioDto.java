package com.republicaargentina.sistemabibliotecabackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.republicaargentina.sistemabibliotecabackend.entity.Usuario}
 */
public record UsuarioDto(@Size(min = 4) @NotBlank String username, @NotBlank String password) implements Serializable {
}