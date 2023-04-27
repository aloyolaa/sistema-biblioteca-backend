package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MaterialService {
    List<Material> getAll();

    Page<Material> pagination(Pageable pageable);

    Material getOne(Long id);

    Material save(Material material);

    Material update(Material material);

    Boolean delete(Long id);

    long count();

    Material getOneByNombre(String nombre);

    Material getOneByCodigo(String codigo);

    List<Material> getAllByNombre(String nombre);

    List<Material> getAllByCodigo(String codigo);

    Page<Material> paginationByArea(Long id, Pageable pageable);

    Material cambiarLetras(Material material);
}
