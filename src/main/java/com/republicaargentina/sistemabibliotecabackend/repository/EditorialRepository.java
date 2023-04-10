package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorialRepository extends JpaRepository<Editorial, Long> {
}