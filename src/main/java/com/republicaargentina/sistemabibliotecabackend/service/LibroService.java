package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Libro;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

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

    ResponseEntity<Resource> exportPDF();

    byte[] exportAllToPdf();

    byte[] exportAllToXls();

    byte[] exportByAreaToPdf(Long id);

    byte[] exportByAreaToXls(Long id);

    byte[] exportByCategoriaToPdf(Long id);

    byte[] exportByCategoriaToXls(Long id);

    byte[] exportByEditorialToPdf(Long id);

    byte[] exportByEditorialToXls(Long id);

    byte[] exportByAutorToPdf(Long id);

    byte[] exportByAutorToXls(Long id);
}
