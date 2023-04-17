package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrestamoMaterialRepository extends JpaRepository<PrestamoMaterial, Long> {
    @Query("select p from PrestamoMaterial p order by p.fechaPrestamo DESC")
    List<PrestamoMaterial> getAll();

    @Query("select p from PrestamoMaterial p order by p.fechaPrestamo DESC")
    Page<PrestamoMaterial> pagination(Pageable pageable);
}