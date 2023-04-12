package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrestamoMaterialService {
    List<PrestamoMaterial> findByOrderByFechaPrestamoDesc();

    Page<PrestamoMaterial> paginationByOrderByFechaPrestamoDesc(Pageable pageable);

    PrestamoMaterial getOne(Long id);

    PrestamoMaterial save(PrestamoMaterial prestamoMaterial);

    PrestamoMaterial update(PrestamoMaterial prestamoMaterial);

    Boolean delete(Long id);

    PrestamoMaterial close(Long id);
}
