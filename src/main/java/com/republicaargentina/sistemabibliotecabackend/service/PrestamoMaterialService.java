package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrestamoMaterialService {
    List<PrestamoMaterial> getAll();

    Page<PrestamoMaterial> pagination(Pageable pageable);

    PrestamoMaterial getOne(Long id);

    PrestamoMaterial save(PrestamoMaterial prestamoMaterial);

    Boolean delete(Long id);

    long count();

    PrestamoMaterial close(PrestamoMaterial prestamoMaterial);

    Page<PrestamoMaterial> paginationByDocente(String dni, Pageable pageable);

    Page<PrestamoMaterial> paginationByGradoAndSeccion(Integer grado, String seccion, Pageable pageable);

    Page<PrestamoMaterial> paginationByFechaPrestamo(String fechaPrestamoStartStr, String fechaPrestamoEndStr, Pageable pageable);

    Page<PrestamoMaterial> paginationByFechaPrestamoAndDocente(String fechaPrestamoStartStr, String fechaPrestamoEndStr, Long id, Pageable pageable);

    Page<PrestamoMaterial> paginationByFechaPrestamoAndGradoAndSeccion(String fechaPrestamoStartStr, String fechaPrestamoEndStr, Integer grado, String seccion, Pageable pageable);

    PrestamoMaterial cambiarLetras(PrestamoMaterial prestamoMaterial);
}
