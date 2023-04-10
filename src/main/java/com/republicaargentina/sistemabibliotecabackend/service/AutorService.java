package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AutorService {
    List<Autor> getAll();

    Page<Autor> pagination(Pageable pageable);

    Autor getOne(Long id);

    Autor save(Autor autor);

    Autor update(Autor autor);

    Boolean delete(Long id);
}
