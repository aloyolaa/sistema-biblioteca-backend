package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.EjemplarLibro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EjemplarLibroRepository extends JpaRepository<EjemplarLibro, Long> {
    @Query("select e from EjemplarLibro e order by e.libro.codigo, e.numero")
    List<EjemplarLibro> getAll();

    @Query("select e from EjemplarLibro e order by e.libro.codigo, e.numero")
    Page<EjemplarLibro> pagination(Pageable pageable);

    @Query(value = "select e from EjemplarLibro e where e.libro.id = ?1 order by e.id desc limit 1")
    Optional<EjemplarLibro> getOneByLibro(Long id);

    @Query("select e from EjemplarLibro e where upper(e.libro.codigo) = upper(?1) order by e.numero")
    Page<EjemplarLibro> paginationByLibro(String codigo, Pageable pageable);

    @Query("select e from EjemplarLibro e where upper(e.libro.codigo) = upper(?1) and e.estado = 'DISPONIBLE' order by e.numero")
    Page<EjemplarLibro> paginationByLibroAndEstado(String codigo, Pageable pageable);

    @Query("select count(e) from EjemplarLibro e where upper(e.libro.codigo) = upper(?1)")
    long countByLibro(String codigo);

    @Query("select count(e) from EjemplarLibro e where upper(e.libro.codigo) = upper(?1) and e.estado = 'DISPONIBLE'")
    long countByLibroAndEstado(String codigo);
}