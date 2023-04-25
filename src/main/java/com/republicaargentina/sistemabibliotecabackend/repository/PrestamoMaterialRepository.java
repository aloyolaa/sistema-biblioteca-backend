package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

public interface PrestamoMaterialRepository extends JpaRepository<PrestamoMaterial, Long> {
    @Query("select p from PrestamoMaterial p order by p.fechaPrestamo DESC")
    List<PrestamoMaterial> getAll();

    @Query("select p from PrestamoMaterial p order by p.fechaPrestamo DESC")
    Page<PrestamoMaterial> pagination(Pageable pageable);

    @Query("select p from PrestamoLibro p where p.fechaPrestamo between ?1 and ?2 order by p.fechaPrestamo DESC")
    Page<PrestamoMaterial> paginationByFechaPrestamo(@Nullable LocalDateTime fechaPrestamoStart, @Nullable LocalDateTime fechaPrestamoEnd, Pageable pageable);

    @Query("select p from PrestamoLibro p where p.docente.dni = ?1 order by p.fechaPrestamo DESC")
    Page<PrestamoMaterial> getAllByDocente(String dni, Pageable pageable);
}