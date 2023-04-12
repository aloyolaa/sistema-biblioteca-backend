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

    List<Libro> findByTituloContainsIgnoreCase(String titulo);

    List<Libro> findByCodigoContainsIgnoreCaseOrderByCodigoAsc(String codigo);

    Page<Libro> findByAutorNombreIgnoreCaseOrderByCodigoAsc(String nombre, Pageable pageable);

    Page<Libro> findByCategoriaNombreIgnoreCaseOrderByCodigoAsc(String nombre, Pageable pageable);

    Page<Libro> findByEditorialNombreIgnoreCaseOrderByCodigoAsc(String nombre, Pageable pageable);

    Page<Libro> findByAreaNombreIgnoreCaseOrderByCodigoAsc(String nombre, Pageable pageable);
}
