package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}