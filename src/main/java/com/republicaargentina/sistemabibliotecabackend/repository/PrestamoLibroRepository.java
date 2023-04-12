package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoLibro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrestamoLibroRepository extends JpaRepository<PrestamoLibro, Long> {
    @Query("select p from PrestamoLibro p order by p.fechaPrestamo DESC")
    List<PrestamoLibro> findByOrderByFechaPrestamoDesc();

    @Query("select p from PrestamoLibro p order by p.fechaPrestamo DESC")
    Page<PrestamoLibro> paginationByOrderByFechaPrestamoDesc(Pageable pageable);
}