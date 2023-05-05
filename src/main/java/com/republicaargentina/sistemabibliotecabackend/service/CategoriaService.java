package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoriaService {
    List<Categoria> getAll();

    Categoria getOne(Long id);

    Categoria save(Categoria categoriaLibro);

    Categoria update(Categoria categoriaLibro);

    Boolean delete(Long id);

    long count();

    Page<Categoria> pagination(Pageable pageable);

    Categoria cambiarLetras(Categoria categoria);
}
