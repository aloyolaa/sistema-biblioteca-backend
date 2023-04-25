package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Editorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EditorialService {
    List<Editorial> getAll();

    Page<Editorial> pagination(Pageable pageable);

    Editorial getOne(Long id);

    Editorial save(Editorial editorial);

    Editorial update(Editorial editorial);

    Boolean delete(Long id);

    long count();

    Editorial cambiarLetras(Editorial editorial);
}
