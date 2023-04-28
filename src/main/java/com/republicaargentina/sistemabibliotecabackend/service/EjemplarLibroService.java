package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.EjemplarLibro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EjemplarLibroService {
    List<EjemplarLibro> getAll();

    Page<EjemplarLibro> pagination(Pageable pageable);

    EjemplarLibro getOne(Long id);

    Boolean save(Long libroId, Integer cantidad);

    EjemplarLibro update(EjemplarLibro ejemplarLibro);

    long count();

    void cambiarPrestado(Long id, Boolean prestado);

    Page<EjemplarLibro> paginationByLibro(String codigo, Pageable pageable);

    Page<EjemplarLibro> paginationByLibroAndEstado(String codigo, Pageable pageable);

    long countByLibro(String codigo);

    long countByLibroAndEstado(String codigo);

    EjemplarLibro cambiarLetras(EjemplarLibro ejemplarLibro);
}
