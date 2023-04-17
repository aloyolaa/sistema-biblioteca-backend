package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Editorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EditorialRepository extends JpaRepository<Editorial, Long> {
    @Query("select e from Editorial e order by e.nombre")
    List<Editorial> getAll();

    @Query("select e from Editorial e order by e.nombre")
    Page<Editorial> pagination(Pageable pageable);
}