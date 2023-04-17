package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoLibro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrestamoLibroService {
    List<PrestamoLibro> getAll();

    Page<PrestamoLibro> pagination(Pageable pageable);

    PrestamoLibro getOne(Long id);

    PrestamoLibro save(PrestamoLibro prestamoLibro);

    Boolean delete(Long id);

    long count();

    PrestamoLibro close(Long id);

    void cambiarEstados();

    Page<PrestamoLibro> paginationByFechaPrestamo(String fechaPrestamoStartStr, String fechaPrestamoEndStr, Pageable pageable);
}
