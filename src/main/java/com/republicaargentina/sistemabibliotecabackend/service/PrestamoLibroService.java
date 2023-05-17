package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoLibro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrestamoLibroService {
    List<PrestamoLibro> getAll();

    PrestamoLibro getOne(Long id);

    PrestamoLibro save(PrestamoLibro prestamoLibro);

    Boolean delete(Long id);

    long count();

    PrestamoLibro close(PrestamoLibro prestamoLibro);

    Page<PrestamoLibro> pagination(Pageable pageable);

    Page<PrestamoLibro> paginationByDocente(String dni, Pageable pageable);

    Page<PrestamoLibro> paginationByGradoAndSeccion(Integer grado, String seccion, Pageable pageable);

    Page<PrestamoLibro> paginationByDescripcion(String descripcion, Pageable pageable);

    Page<PrestamoLibro> paginationByFechaPrestamo(String fechaPrestamoStartStr, String fechaPrestamoEndStr, Pageable pageable);

    Page<PrestamoLibro> paginationByFechaPrestamoAndDocente(String fechaPrestamoStartStr, String fechaPrestamoEndStr, Long id, Pageable pageable);

    Page<PrestamoLibro> paginationByFechaPrestamoAndGradoAndSeccion(String fechaPrestamoStartStr, String fechaPrestamoEndStr, Integer grado, String seccion, Pageable pageable);

    Page<PrestamoLibro> paginationByFechaPrestamoAndDescripcion(String fechaPrestamoStartStr, String fechaPrestamoEndStr, String descripcion, Pageable pageable);

    PrestamoLibro cambiarLetras(PrestamoLibro prestamoLibro);
}
