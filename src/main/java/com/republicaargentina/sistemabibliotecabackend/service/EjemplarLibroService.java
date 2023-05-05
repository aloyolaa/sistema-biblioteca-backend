package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.EjemplarLibro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EjemplarLibroService {
    List<EjemplarLibro> getAll();

    EjemplarLibro getOne(Long id);

    Boolean save(Long libroId, Integer cantidad);

    EjemplarLibro update(EjemplarLibro ejemplarLibro);

    long count();

    List<EjemplarLibro> getAllByLibroAndEstado(String codigo, Integer cantidad);

    long countByLibro(String codigo);

    long countByLibroAndEstado(String codigo);

    Page<EjemplarLibro> pagination(Pageable pageable);

    Page<EjemplarLibro> paginationByLibro(String codigo, Pageable pageable);

    void cambiarPrestado(Long id, Boolean prestado);

    EjemplarLibro cambiarLetras(EjemplarLibro ejemplarLibro);
}
