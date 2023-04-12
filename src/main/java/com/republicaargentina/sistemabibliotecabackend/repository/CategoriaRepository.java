package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("select c from Categoria c order by c.nombre")
    List<Categoria> findByOrderByNombreAsc();

    @Query("select c from Categoria c order by c.nombre")
    Page<Categoria> paginationByOrderByNombreAsc(Pageable pageable);
}