package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.EjemplarMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EjemplarMaterialService {
    List<EjemplarMaterial> getAll();

    EjemplarMaterial getOne(Long id);

    Boolean save(Long materialId, Integer cantidad);

    EjemplarMaterial update(EjemplarMaterial ejemplarMaterial);

    long count();

    List<EjemplarMaterial> getAllByMaterialAndEstado(String codigo, Integer cantidad);

    long countByMaterial(String codigo);

    long countByMaterialAndEstado(String codigo);

    Page<EjemplarMaterial> pagination(Pageable pageable);

    Page<EjemplarMaterial> paginationByMaterial(String codigo, Pageable pageable);

    void cambiarPrestado(Long id, Boolean prestado);

    EjemplarMaterial cambiarLetras(EjemplarMaterial ejemplarMaterial);
}
