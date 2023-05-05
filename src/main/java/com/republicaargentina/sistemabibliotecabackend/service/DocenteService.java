package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Docente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DocenteService {
    List<Docente> getAll();

    Docente getOne(Long id);

    Docente save(Docente docente);

    Docente update(Docente docente);

    Boolean delete(Long id);

    long count();

    Docente getOneByDni(String dni);

    Page<Docente> pagination(Pageable pageable);

    Docente cambiarLetras(Docente docente);
}
