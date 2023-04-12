package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoLibro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrestamoLibroService {
    List<PrestamoLibro> findByOrderByFechaPrestamoDesc();

    Page<PrestamoLibro> paginationByOrderByFechaPrestamoDesc(Pageable pageable);

    PrestamoLibro getOne(Long id);

    PrestamoLibro save(PrestamoLibro prestamoLibro);

    PrestamoLibro update(PrestamoLibro prestamoLibro);

    Boolean delete(Long id);

    PrestamoLibro close(Long id);
}
