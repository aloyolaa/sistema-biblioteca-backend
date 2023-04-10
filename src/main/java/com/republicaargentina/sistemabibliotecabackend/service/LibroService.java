package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LibroService {
    List<Libro> getAll();

    Page<Libro> pagination(Pageable pageable);

    Libro getOne(Long id);

    Libro save(Libro libro);

    Libro update(Libro libro);

    Boolean delete(Long id);
}
