package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Docente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    @Query("select d from Docente d order by d.apellido, d.nombre")
    List<Docente> getAll();

    @Query("select d from Docente d where d.dni = ?1")
    Optional<Docente> getOneByDni(String dni);

    @Query("select d from Docente d order by d.apellido, d.nombre")
    Page<Docente> pagination(Pageable pageable);
}
