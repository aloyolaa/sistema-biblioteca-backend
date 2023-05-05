package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    @Query("select a from Area a order by a.nombre")
    List<Area> getAll();

    @Query("select a from Area a order by a.nombre")
    Page<Area> pagination(Pageable pageable);
}
