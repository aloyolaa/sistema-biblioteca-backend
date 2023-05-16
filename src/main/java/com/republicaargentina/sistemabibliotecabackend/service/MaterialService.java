package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Material;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MaterialService {
    List<Material> getAll();

    Material getOne(Long id);

    Material save(Material material);

    Material update(Material material);

    Boolean delete(Long id);

    long count();

    Material getOneByNombre(String nombre);

    Material getOneByCodigo(String codigo);

    Page<Material> pagination(Pageable pageable);

    Page<Material> paginationByNombre(String nombre, Pageable pageable);

    Page<Material> paginationByCodigo(String codigo, Pageable pageable);

    Page<Material> paginationByArea(Long id, Pageable pageable);

    Material cambiarLetras(Material material);

    ResponseEntity<Resource> exportPDF();
}
