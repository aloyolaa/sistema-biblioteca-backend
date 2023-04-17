package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Ejemplar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EjemplarService {
    List<Ejemplar> getAll();

    Page<Ejemplar> pagination(Pageable pageable);

    Ejemplar getOne(Long id);

    Boolean save(Long libroId, Integer cantidad);

    Ejemplar update(Ejemplar ejemplar);

    long count();

    void cambiarEstadoAPrestado(Long id);

    void cambiarEstadoADisponible(Long id);

    Page<Ejemplar> paginationByLibro(String codigo, Pageable pageable);

    Page<Ejemplar> paginationByLibroAndEstado(String codigo, Pageable pageable);

    long countByLibro(String codigo);
}
