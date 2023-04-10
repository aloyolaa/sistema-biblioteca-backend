package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}