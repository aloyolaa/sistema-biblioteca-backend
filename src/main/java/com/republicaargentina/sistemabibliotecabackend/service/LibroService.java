package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LibroService {
    List<Libro> getAll();

    Libro getOne(Long id);

    Libro save(Libro libro);

    Libro update(Libro libro);

    Boolean delete(Long id);

    long count();

    Libro getOneByTitulo(String titulo);

    Libro getOneByCodigo(String codigo);

    Page<Libro> pagination(Pageable pageable);

    Page<Libro> paginationByTitulo(String titulo, Pageable pageable);

    Page<Libro> paginationByCodigo(String codigo, Pageable pageable);

    Page<Libro> paginationByArea(Long id, Pageable pageable);

    Page<Libro> paginationByCategoria(Long id, Pageable pageable);

    Page<Libro> paginationByEditorial(Long id, Pageable pageable);

    Page<Libro> paginationByAutor(Long id, Pageable pageable);

    Libro cambiarLetras(Libro libro);
}
