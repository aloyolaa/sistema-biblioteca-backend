package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Ejemplar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {
    @Query("select e from Ejemplar e order by e.libro.codigo, e.numero")
    List<Ejemplar> getAll();

    @Query("select e from Ejemplar e order by e.libro.codigo, e.numero")
    Page<Ejemplar> pagination(Pageable pageable);

    @Query(value = "select e from Ejemplar e where e.libro.id = ?1 order by e.id desc limit 1")
    Optional<Ejemplar> getOneByLibro(Long id);

    @Query("select e from Ejemplar e where upper(e.libro.codigo) = upper(?1) order by e.numero")
    Page<Ejemplar> paginationByLibro(String codigo, Pageable pageable);

    @Query("select e from Ejemplar e where upper(e.libro.codigo) = upper(?1) and e.estado = 'DISPONIBLE' order by e.numero")
    Page<Ejemplar> paginationByLibroAndEstado(String codigo, Pageable pageable);

    @Query("select count(e) from Ejemplar e where upper(e.libro.codigo) = upper(?1)")
    long countByLibro(String codigo);
}